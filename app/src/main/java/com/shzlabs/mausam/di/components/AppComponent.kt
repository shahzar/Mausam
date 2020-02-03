package com.shzlabs.mausam.di.components

import android.app.Application
import com.shzlabs.mausam.di.ViewModelBuilder
import com.shzlabs.mausam.di.modules.AppModule
import com.shzlabs.mausam.di.modules.NetworkModule
import com.shzlabs.mausam.ui.home.HomeFragment
import com.shzlabs.mausam.ui.home.HomeViewModel
import com.shzlabs.mausam.MainActivity
import dagger.Component

@Component(modules = [AppModule::class, NetworkModule::class, ViewModelBuilder::class])
interface AppComponent {

    fun application(application: Application)
    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(homeViewModel: HomeViewModel)

}