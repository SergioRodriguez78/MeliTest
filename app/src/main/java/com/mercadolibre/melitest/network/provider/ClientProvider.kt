package com.mercadolibre.melitest.network.provider

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class ClientProvider {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    fun provide(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            json.asConverterFactory(
                "application/json".toMediaType()
            )
        ).build()

    companion object {
        private const val BASE_URL = "https://api.mercadolibre.com/"
    }
}
