package com.shzlabs.mausam.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shzlabs.mausam.ui.forecast.ForecastViewModel
import com.shzlabs.mausam.ui.search.SearchViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class ViewModelFactory @Inject constructor (val creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creators[modelClass]?.get() as T
    }

}

@Module
internal abstract class ViewModelBuilder {
    @Binds
    internal abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory


    // Add all ViewModels here

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun searchViewModel(searchViewModel: SearchViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ForecastViewModel::class)
    internal abstract fun forecastViewModel(forecastViewModel: ForecastViewModel) : ViewModel

}

@Target(
    AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)