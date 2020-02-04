package com.shzlabs.mausam.di.components

import android.app.Application
import com.shzlabs.mausam.di.ViewModelBuilder
import com.shzlabs.mausam.di.modules.AppModule
import com.shzlabs.mausam.di.modules.NetworkModule
import com.shzlabs.mausam.ui.search.SearchFragment
import com.shzlabs.mausam.ui.search.SearchViewModel
import com.shzlabs.mausam.MainActivity
import com.shzlabs.mausam.ui.forecast.ForecastFragment
import dagger.Component

@Component(modules = [AppModule::class, NetworkModule::class, ViewModelBuilder::class])
interface AppComponent {

    fun application(application: Application)
    fun inject(mainActivity: MainActivity)
    fun inject(searchFragment: SearchFragment)
    fun inject(forecastFragment: ForecastFragment)
    fun inject(homeViewModel: SearchViewModel)

}