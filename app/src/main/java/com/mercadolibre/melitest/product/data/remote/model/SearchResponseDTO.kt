package com.mercadolibre.melitest.product.data.remote.model

import com.mercadolibre.melitest.product.data.remote.model.product.ProductDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponseDTO(
    @SerialName("site_id")
    val siteId: String,

    @SerialName("country_default_time_zone")
    val countryDefaultTimeZone: String,

    val query: String,

    val paging: PagingDTO,

    val results: List<ProductDTO>
)
