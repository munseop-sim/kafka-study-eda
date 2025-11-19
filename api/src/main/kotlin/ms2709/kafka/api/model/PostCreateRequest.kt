package ms2709.kafka.api.model

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class PostCreateRequest {
    @field:NotBlank(message = "Title is required")
    var title: String? = null

    @field:NotNull(message = "User ID is required")
    @field:Positive(message = "User ID must be positive")
    var userId: Long? = null

    @field:NotBlank(message = "Content is required")
    var content: String? = null

    @field:NotNull(message = "Category ID is required")
    @field:Positive(message = "Category ID must be positive")
    var categoryId: Long? = null
}