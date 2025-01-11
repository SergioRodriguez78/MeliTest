package com.mercadolibre.melitest.product.data.remote.model.product

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressDTO(
    @SerialName("state_name")
    val stateName: String,

    @SerialName("city_name")
    val cityName: String
)
