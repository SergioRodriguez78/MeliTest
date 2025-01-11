package com.mercadolibre.melitest.product.data

import com.mercadolibre.melitest.product.model.Product

interface ProductRepository {
    suspend fun getProducts(query: String): Result<List<Product>>
}
