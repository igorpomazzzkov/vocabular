package dto

import kotlinx.serialization.Serializable

@Serializable
data class AddSupplier(
    val name: String,
    val description: String? = null
)

@Serializable
data class UpdateSupplier(
    val id: String,
    val name: String? = null,
    val description: String? = null
)

