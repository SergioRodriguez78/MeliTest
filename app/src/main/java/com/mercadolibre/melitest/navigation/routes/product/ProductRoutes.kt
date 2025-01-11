package com.mercadolibre.melitest.navigation.routes.product

import kotlinx.serialization.Serializable

sealed class ProductRoutes {

    @Serializable
    data object SearchProduct : ProductRoutes()

    @Serializable
    data class ProductList(val query: String) : ProductRoutes()

    @Serializable
    data class ProductDetail(val productId: String) : ProductRoutes()
}
