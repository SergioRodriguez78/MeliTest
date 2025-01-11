package com.mercadolibre.melitest.product.data.remote

import com.mercadolibre.melitest.product.data.remote.model.SearchResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET("sites/MCO/search")
    suspend fun searchProducts(
        @Query("q") query: String
    ): Response<SearchResponseDTO>
}
