package com.kabms.tvviewer

import android.app.Application
import com.kabms.tvviewer.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(AppModule.getModule())
        }
    }
}