package com.shzlabs.mausam.ui.base

import androidx.appcompat.app.AppCompatActivity
import com.shzlabs.mausam.BaseApplication
import com.shzlabs.mausam.di.components.AppComponent

open class BaseActivity: AppCompatActivity() {

    fun getDiComponent() : AppComponent {
        return (application as BaseApplication).appComponent
    }

}