package com.mercadolibre.melitest.product.data

import com.mercadolibre.melitest.product.data.remote.ProductService
import com.mercadolibre.melitest.product.model.Product
import com.mercadolibre.melitest.product.model.toProductList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepositoryImpl(
    private val service: ProductService
) : ProductRepository {

    override suspend fun getProducts(query: String): Result<List<Product>> =
        withContext(Dispatchers.IO) {

            val response = service.searchProducts(query = query)

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body.toProductList())
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("Error: ${response.message()} (Code: ${response.code()})"))
            }
        }
}

