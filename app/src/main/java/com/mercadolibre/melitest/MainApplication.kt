package com.mercadolibre.melitest

import android.app.Application
import com.mercadolibre.melitest.di.KoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

open class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                KoinModules.network,
                KoinModules.product,
            )
        }
    }
}
