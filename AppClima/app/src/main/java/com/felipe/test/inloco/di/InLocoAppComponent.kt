package com.felipe.test.inloco.di

import android.content.Context
import com.felipe.test.inloco.features.map.MapViewModel
import dagger.Component
import javax.inject.Singleton
import dagger.BindsInstance



@Singleton
@Component(modules = [
    RetrofitModule::class
])
interface InLocoAppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder
        fun build(): InLocoAppComponent
    }

    fun mapViewModelFactory(): ViewModelFactory<MapViewModel>
}