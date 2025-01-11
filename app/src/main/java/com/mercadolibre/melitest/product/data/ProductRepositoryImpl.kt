package com.mercadolibre.melitest.product.data

import com.mercadolibre.melitest.product.data.remote.ProductService
import com.mercadolibre.melitest.product.model.Product
import com.mercadolibre.melitest.product.model.toProductList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepositoryImpl(
    private val service: ProductService
) : ProductRepository {

    private var productList = emptyList<Product>()

    override suspend fun getProducts(query: String): Result<List<Product>> =
        withContext(Dispatchers.IO) {

            val response = service.searchProducts(query = query)

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {

                    val products = body.toProductList()

                    // Have an instance in cache of the current list of products
                    productList = products

                    Result.success(products)

                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("Error: ${response.message()} (Code: ${response.code()})"))
            }
        }

    override suspend fun getProductDetail(productId: String): Result<Product> {
        val product = productList.find { it.id == productId }
        return if (product != null) {
            Result.success(product)
        } else {
            Result.failure(Exception("Product not found"))
        }
    }

}
