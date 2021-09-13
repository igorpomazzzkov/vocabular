package dto

import entity.Supplier
import entity.VocabularId
import kotlinx.serialization.Serializable

@Serializable
data class WordDto(
    val id: String? = null,
    val en: String? = null,
    val ru: String? = null,
    val attempt: Int? = null,
    val supplierId: VocabularId? = null,
    val supplier: Supplier? = null
)

@Serializable
data class AddWordDto(
    val en: String,
    val ru: String,
    val supplier: VocabularId? = null
)