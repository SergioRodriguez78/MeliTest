package com.mercadolibre.melitest.product.data.remote.model.product

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDTO(
    val id: String,
    val title: String,
    val condition: String,
    val thumbnail: String,
    val permalink: String,
    val price: Double,
    @SerialName("available_quantity")
    val availableQuantity: Int,
    val seller: Seller,
    val address: Address,
)
