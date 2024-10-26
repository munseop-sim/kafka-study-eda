package ms2709.kafka.usecase.core.port.metadata

interface MetadataPort {
    fun getCategoryNameByCategoryId(categoryId: Long): String?
    fun getUserNameByUserId(userId: Long): String?
    fun listFollowerIdsByUserId(userId: Long): List<Long>
}