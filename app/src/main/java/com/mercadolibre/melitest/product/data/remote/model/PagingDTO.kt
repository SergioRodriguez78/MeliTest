package com.mercadolibre.melitest.product.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagingDTO(
    val total: Int,
    @SerialName("primary_results")
    val primaryResults: Int,
    val offset: Int,
    val limit: Int
)
