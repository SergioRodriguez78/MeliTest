package com.mercadolibre.melitest.di

import com.mercadolibre.melitest.network.provider.ClientProvider
import com.mercadolibre.melitest.product.search.viewmodel.SearchScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

object KoinModules {
    val network = module {
        single { ClientProvider().provide() }
    }

    val product = module {
        // Search
        viewModelOf(::SearchScreenViewModel)
    }
}
