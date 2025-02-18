package com.mercadolibre.melitest.product.data.remote.model.product

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SellerDTO(
    val id: Long,
    @SerialName("nickname")
    val name: String,
)
