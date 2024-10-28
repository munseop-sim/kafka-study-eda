package ms2709.kafka.usecase.inspected_post_usecase

import inspectedpost.model.AutoInspectionResultTypes
import inspectedpost.model.InspectedPost

import ms2709.kafka.usecase.core.port.chatgpt.PostAutoInspectPort
import ms2709.kafka.usecase.core.port.metadata.MetadataPort
import org.springframework.stereotype.Service
import post.model.Post

@Service
open class PostInspectService(
    private val metadataPort: MetadataPort,
    private val postAutoInspectPort: PostAutoInspectPort
) : PostInspectUseCase{
    override fun inspectAndGetIfValid(post: Post): InspectedPost? {
        assert(post.categoryId != null)
        var categoryName:String? =null
        return metadataPort.getCategoryNameByCategoryId(post.categoryId!!)?.let {
            categoryName = it
            postAutoInspectPort.inspect(post, categoryName!!)
        }?.let {
            if(it.status == AutoInspectionResultTypes.BAD){
                null
            }else{
                InspectedPost(post,categoryName!!, it.tags.toList())
            }
        }
    }
}