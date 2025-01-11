package com.mercadolibre.melitest.product.data

import com.mercadolibre.melitest.core.ResultMELI
import com.mercadolibre.melitest.product.data.remote.ProductService
import com.mercadolibre.melitest.product.model.Product
import com.mercadolibre.melitest.product.model.toProductList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepositoryImpl(
    private val service: ProductService
) : ProductRepository {

    private var productList = emptyList<Product>()

    override suspend fun getProducts(query: String): ResultMELI<List<Product>> =
        withContext(Dispatchers.IO) {

            val response = service.searchProducts(query = query)

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {

                    val products = body.toProductList()

                    // Have an instance in cache of the current list of products
                    productList = products

                    ResultMELI.success(products)

                } else {
                    ResultMELI.error(Exception("Response body is null"))
                }
            } else {
                ResultMELI.error(Exception("Error: ${response.message()} (Code: ${response.code()})"))
            }
        }

    override suspend fun getProductDetail(productId: String): ResultMELI<Product> {
        val product = productList.find { it.id == productId }
        return if (product != null) {
            ResultMELI.success(product)
        } else {
            ResultMELI.error(Exception("Product not found"))
        }
    }

}
