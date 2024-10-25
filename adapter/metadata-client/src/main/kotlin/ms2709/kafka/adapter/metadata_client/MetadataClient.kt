package ms2709.kafka.adapter.metadata_client

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class MetadataClient (
    private val metadataWebClient: WebClient
){

    fun getCategoryById(
        categoryId: Long
    ): CategoryResponse {
        return metadataWebClient
            .get()
            .uri("/categories/$categoryId")
            .retrieve()
            .bodyToMono(CategoryResponse::class.java)
            .block()
    }


    fun getUserById(
        userId: Long
    ): UserResponse {
        return metadataWebClient
            .get()
            .uri("/users/$userId")
            .retrieve()
            .bodyToMono<UserResponse>(UserResponse::class.java)
            .block()
    }

    fun getFollowerIdsByUserId(userId: Long): List<Long> {
        return metadataWebClient
            .get()
            .uri("/followers?followingId=$userId")
            .retrieve()
            .bodyToFlux(Long::class.java)
            .collectList()
            .block()
    }


    class CategoryResponse{
        var id:Long? = null
        var name: String? = null

        override fun toString(): String {
            return "CategoryResponse(id=$id, name=$name)"
        }
    }

    class UserResponse{
        @JsonProperty
        var id:Long? = null

        @JsonProperty
        var email:String? = null

        @JsonProperty
        var name:String? = null

        override fun toString(): String {
            return "UserResponse(id=$id, email=$email, name=$name)"
        }
    }
}