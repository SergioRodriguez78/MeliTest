package com.mercadolibre.melitest.di

import com.mercadolibre.melitest.network.provider.ClientProvider
import org.koin.dsl.module

object KoinModules {
    val network = module {
        single { ClientProvider().provide() }
    }
}
