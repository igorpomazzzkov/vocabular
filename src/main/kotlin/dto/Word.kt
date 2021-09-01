package dto

data class WordDto(
    val id: String,
    val en: String,
    val ru: String
)

data class AddWord(
    val en: String,
    val ru: String
)