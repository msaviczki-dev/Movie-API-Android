package com.freecast.thatmovieapp.app

import android.app.Application
import com.freecast.thatmovieapp.di.ModuleProvider
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(ModuleProvider().provide())
        }
    }
}