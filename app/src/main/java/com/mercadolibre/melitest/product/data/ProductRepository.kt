package com.mercadolibre.melitest.product.data

import com.mercadolibre.melitest.core.ResultMELI
import com.mercadolibre.melitest.product.model.Product

interface ProductRepository {
    suspend fun getProducts(query: String): ResultMELI<List<Product>>
    suspend fun getProductDetail(productId: String): ResultMELI<Product>
}
