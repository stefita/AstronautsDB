package com.stefita.astronautsdb

import android.app.Application
import com.facebook.stetho.Stetho
import com.stefita.astronautsdb.di.networkModules
import com.stefita.astronautsdb.di.repositoryModule
import com.stefita.astronautsdb.di.useCaseModule
import com.stefita.astronautsdb.di.viewModels
import com.stefita.astronautsdb.presentation.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()
        attachStetho()
    }

    private fun loadKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                networkModules,
                viewModels,
                repositoryModule,
                useCaseModule
            )
        }
    }

    private fun attachStetho() {
        if (BuildConfig.DEBUG) {
            // only enable for debug builds
            Stetho.initializeWithDefaults(this)
        }
    }
}