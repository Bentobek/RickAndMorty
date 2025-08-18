package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.data.module.dataModule
import com.example.rickandmorty.ui.module.uiModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModule, uiModel)
        }

    }
}