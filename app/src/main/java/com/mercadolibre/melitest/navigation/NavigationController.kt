package com.mercadolibre.melitest.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.mercadolibre.melitest.navigation.routes.product.ProductRoutes
import com.mercadolibre.melitest.product.detail.ui.screen.ProductDetailScreen
import com.mercadolibre.melitest.product.list.ui.screen.ProductListScreen
import com.mercadolibre.melitest.product.search.ui.screen.SearchScreen

@Composable
fun NavigationController(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        navController = navController,
        startDestination = ProductRoutes.SearchProduct,
    ) {
        composable<ProductRoutes.SearchProduct> {
            SearchScreen(
                navController
            )
        }

        composable<ProductRoutes.ProductList> { backStackEntry ->
            val query = backStackEntry.toRoute<ProductRoutes.ProductList>()
            ProductListScreen(
                query = query,
                navigator = navController,
            )
        }

        composable<ProductRoutes.ProductDetail> { backStackEntry ->
            val product = backStackEntry.toRoute<ProductRoutes.ProductDetail>()
            ProductDetailScreen(
                productId = product.productId,
            )
        }
    }
}
