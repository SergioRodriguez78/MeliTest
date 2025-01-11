package com.mercadolibre.melitest.di

import com.mercadolibre.melitest.network.provider.ClientProvider
import com.mercadolibre.melitest.product.data.ProductRepository
import com.mercadolibre.melitest.product.data.ProductRepositoryImpl
import com.mercadolibre.melitest.product.detail.viewmodel.ProductDetailViewModel
import com.mercadolibre.melitest.product.list.viewmodel.ProductListViewModel
import com.mercadolibre.melitest.product.search.viewmodel.SearchScreenViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

object KoinModules {
    val network = module {
        single { ClientProvider().provide() }
    }

    val product = module {
        // Data
        single { ClientProvider().provideProductService(get()) }
        singleOf(::ProductRepositoryImpl) { bind<ProductRepository>() }

        // Search
        viewModelOf(::SearchScreenViewModel)

        // List
        viewModelOf(::ProductListViewModel)

        // Detail
        viewModelOf(::ProductDetailViewModel)
    }
}
