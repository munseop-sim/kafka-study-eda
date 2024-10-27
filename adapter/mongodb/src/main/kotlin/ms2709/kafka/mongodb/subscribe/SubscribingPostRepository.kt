package ms2709.kafka.mongodb.subscribe

import org.springframework.data.mongodb.repository.MongoRepository

interface SubscribingPostRepository : SubscribingPostCustomRepository, MongoRepository<SubscribingPostDocument, String> {
}