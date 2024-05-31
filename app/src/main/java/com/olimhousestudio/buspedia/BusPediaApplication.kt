package com.olimhousestudio.buspedia

import android.app.Application
import com.olimhousestudio.buspedia.di.AppModule
import com.olimhousestudio.buspedia.di.AppModuleImpl

class BusPediaApplication : Application() {
    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl()
    }
}