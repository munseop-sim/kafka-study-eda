package ms2709.kafka.elasticsearch

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface PostDocumentRepository : ElasticsearchRepository<PostDocument, Long> {
}