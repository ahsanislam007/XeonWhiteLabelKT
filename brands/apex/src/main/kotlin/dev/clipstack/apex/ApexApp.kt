package dev.clipstack.apex

import android.app.Application
import dev.clipstack.apex.di.apexModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApexApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ApexApp)
            modules(apexModules)
        }
    }
}
