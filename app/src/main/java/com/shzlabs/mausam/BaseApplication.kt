package com.shzlabs.mausam

import android.app.Application
import com.shzlabs.mausam.di.modules.AppModule
import com.shzlabs.mausam.di.components.AppComponent
import com.shzlabs.mausam.di.components.DaggerAppComponent

class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent.application(this)
    }

}