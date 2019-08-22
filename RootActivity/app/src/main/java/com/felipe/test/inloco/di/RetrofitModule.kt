package com.felipe.test.inloco.di

import com.felipe.test.inloco.WebApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides @Singleton
    fun provideWebApi(): WebApi = Retrofit.Builder()
        .baseUrl("http://api.openweathermap.org/data/2.5/")
        .build()
        .create(WebApi::class.java)
}