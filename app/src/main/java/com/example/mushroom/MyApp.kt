package com.example.mushroom

import android.app.Application
import com.example.mushroom.di.repoModule
import com.example.mushroom.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(viewModelModule,repoModule)
        }
    }
}