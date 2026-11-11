package dev.clipstack.bloom

import android.app.Application
import dev.clipstack.bloom.di.bloomModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BloomApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BloomApp)
            modules(bloomModules)
        }
    }
}
