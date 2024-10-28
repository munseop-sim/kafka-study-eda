package ms2709.kafka.adapter.chatgpt

import inspectedpost.model.AutoInspectionResult
import ms2709.kafka.common.CustomObjectMapper
import ms2709.kafka.common.LogDelegate

import ms2709.kafka.usecase.core.port.chatgpt.PostAutoInspectPort
import org.springframework.stereotype.Component
import post.model.Post

@Component
class PostAutoInspectAdapter(
    private val chatGptClient: ChatGptClient
) : PostAutoInspectPort {
    private val objectMapper = CustomObjectMapper()
    private val log by LogDelegate()

    override fun inspect(post: Post, categoryName: String): AutoInspectionResult? {
        val contentString = "[${categoryName}] ${post.title} - ${post.content}"
        return runCatching {

            chatGptClient.getPostInspectResultContentWithPolicy(contentString, AutoInspectionPolicy).run {
                log.info("result -> {}", this)
                objectMapper.readValue(this, AutoInspectionResult::class.java)
            }
        }.onFailure {
            log.info("PostAutoInspectAdapter.inspect error -> {}",it.message)
        }.getOrNull()

    }

    internal object AutoInspectionPolicy : GptInspectionPolicy
    {
        override val instruction: String
            get() =  ("The task you need to accomplish is to return two items ('status' and 'tags') in JSON format. " +
                    "The information I will provide will be in the format '[Post category] Post content.' " +
                    "Then, if the content of the post aligns well with the meaning or theme of the post category, " +
                    "fill the 'status' field with the string 'GOOD.' " +
                    "If the meaning or theme appears unrelated, " +
                    "fill the 'status' field with the string 'BAD.' " +
                    "Additionally, extract and compile a list of up to 5 keywords " +
                    "that seem most important in the post content and populate the 'tags' field with them.")
        override val example: String
            get() = ( "[Health] Reps and Muscle Size - To increase muscle size, " +
                    "it is considered most ideal to exercise with the maximum weight " +
                    "that allows 8 to 12 repetitions per set.")

        override val exampleResult: String
            get() = "{\"status\":\"GOOD\",\"tags\":[\"muscle\", \"weight\", \"repetitions\"]}"
    }
}