package com.mercadolibre.melitest.core

import com.mercadolibre.melitest.navigation.routes.product.ProductRoutes

sealed class GeneralEvent {
    data class Navigation(val route: ProductRoutes) : GeneralEvent()
}
