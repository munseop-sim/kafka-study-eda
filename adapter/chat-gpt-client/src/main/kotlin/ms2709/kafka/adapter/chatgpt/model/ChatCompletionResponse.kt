package ms2709.kafka.adapter.chatgpt.model

import com.fasterxml.jackson.annotation.JsonProperty

class ChatCompletionResponse {
    var id:String? = null
    @JsonProperty("object")
    var objectResult:String? = null
    var created:Long? = null
    var model:String? = null
    var choices:List<ChatChoice> = mutableListOf()
    var usage:Usage? = null
    var systemFingerprint:String? = null



    class ChatChoice{
        var index:Int? = null
        var message:Message? = null
        var logprobs:Any? = null
        @JsonProperty("finish_reason")
        var finishReason:String? = null
    }

    class Usage{
        @JsonProperty("prompt_tokens")
        var promptTokens:Int? = null

        @JsonProperty("completion_tokens")
        var completionTokens:Int? = null

        @JsonProperty("total_tokens")
        private var totalTokens: Int? = null
    }
    class Message{
        var role:String?= null
        var content:String? =null
        var refusal:String? = null
    }
}