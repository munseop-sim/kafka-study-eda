package ms2709.kafka.elasticsearch.config

import org.apache.http.HttpHeaders
import org.apache.http.HttpHost
import org.apache.http.HttpResponse
import org.apache.http.HttpResponseInterceptor
import org.apache.http.client.CredentialsProvider
import org.apache.http.impl.client.BasicCredentialsProvider
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder
import org.apache.http.message.BasicHeader
import org.apache.http.protocol.HttpContext
import org.elasticsearch.client.RestClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.List

@Configuration
open class ElasticsearchConfig {
    @Value("\${spring.data.elasticsearch.host}")
    private val esHost: String? = null

    @Value("\${spring.data.elasticsearch.port}")
    private val esPort: Int? = null

    @Bean
    open fun restClient(): RestClient{
        val credentialsProvider: CredentialsProvider = BasicCredentialsProvider()
        return RestClient.builder(HttpHost(esHost, esPort!!))
            .setHttpClientConfigCallback { httpClientBuilder: HttpAsyncClientBuilder ->
                httpClientBuilder.disableAuthCaching()
                httpClientBuilder.setDefaultHeaders(
                    List.of(
                        BasicHeader(
                            HttpHeaders.CONTENT_TYPE,
                            "application/json"
                        )
                    )
                )
                httpClientBuilder.addInterceptorLast(HttpResponseInterceptor { response: HttpResponse, context: HttpContext? ->
                    response.addHeader(
                        "X-Elastic-Product",
                        "Elasticsearch"
                    )
                }
                )
                httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)
            }.build()
    }

}
