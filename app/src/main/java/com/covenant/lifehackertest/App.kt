package com.covenant.lifehackertest

import android.app.Application
import android.content.Context
import com.covenant.lifehackertest.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        lateinit var appCtx: Context
    }

    //==============================================================================================

    override fun onCreate() {
        super.onCreate()

        // Application context
        appCtx = applicationContext

        // Koin init
        startKoin {
            androidContext(this@App)
            modules(NetworkModule)
        }
    }


}