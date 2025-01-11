package com.mercadolibre.melitest.product.data.remote.model.product

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Seller(
    val id: Int,
    @SerialName("nickname")
    val name: String,
)
