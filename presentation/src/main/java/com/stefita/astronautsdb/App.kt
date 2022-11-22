package com.stefita.astronautsdb

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.facebook.stetho.Stetho
import com.stefita.astronautsdb.di.repositoryModule
import com.stefita.astronautsdb.di.useCaseModule
import com.stefita.astronautsdb.di.viewModels
import com.stefita.astronautsdb.presentation.BuildConfig
import com.stefita.data.di.dbModule
import com.stefita.data.di.networkModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application(), ImageLoaderFactory {

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
                dbModule,
                repositoryModule,
                useCaseModule
            )
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .respectCacheHeaders(false)
            .memoryCache {
                MemoryCache.Builder(this@App)
                    .maxSizePercent(Config.Coil.CACHE_MEMORY)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(cacheDir.resolve(Config.Coil.CACHE_DIR))
                    .maxSizeBytes(Config.Coil.CACHE_DISK_MAX_SIZE_BYTES)
                    .build()
            }
            .build()
    }

    private fun attachStetho() {
        if (BuildConfig.DEBUG) {
            // only enable for debug builds
            Stetho.initializeWithDefaults(this)
        }
    }
}

object Config {
    object Coil {
        const val CACHE_DIR = "image_cache"
        const val CACHE_DISK_MAX_SIZE_BYTES = 512L * 1024 * 1024
        const val CACHE_MEMORY = 0.25
    }
}