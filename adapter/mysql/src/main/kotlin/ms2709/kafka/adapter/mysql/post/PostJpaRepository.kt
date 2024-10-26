package ms2709.kafka.adapter.mysql.post

import org.springframework.data.jpa.repository.JpaRepository

interface PostJpaRepository : JpaRepository<PostEntity, Long?>{
}