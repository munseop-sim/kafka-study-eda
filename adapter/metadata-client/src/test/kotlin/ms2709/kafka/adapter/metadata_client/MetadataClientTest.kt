package ms2709.kafka.adapter.metadata_client

import ms2709.kafka.adapter.metadata_client.config.MetadataWebClientConfig
import ms2709.kafka.common.LogDelegate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [MetadataWebClientConfig::class, MetadataClient::class])
@SpringBootTest
class MetadataClientTest @Autowired constructor (
    private val sut: MetadataClient
){
    private val logger by LogDelegate()

    @Disabled("api 작동 확인용")
    @Test
    fun getCategoryByIdTest(){
        //given
        val categoryId = 1L

        //when
        val category = sut.getCategoryById(categoryId)

        //then
        assertThat(category).isNotNull
        logger.info("category: $category")
    }

    @Disabled("api 작동 확인용")
    @Test
    fun getFollowerIdsByUserId(){
        //given
        val userId = 2L

        //when
        val followerIdList = sut.getFollowerIdsByUserId(userId)

        //then
        assertThat(followerIdList).isNotNull
        logger.info("followerIdList: $followerIdList")
    }
}