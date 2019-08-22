package com.felipe.test.inloco

import android.app.Application
import com.felipe.test.inloco.di.DaggerComponentProvider
import com.felipe.test.inloco.di.DaggerInLocoAppComponent
import com.felipe.test.inloco.di.InLocoAppComponent

class AppAplication: Application(), DaggerComponentProvider {


    override val component: InLocoAppComponent by lazy {
        DaggerInLocoAppComponent.builder()
            .applicationContext(applicationContext)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}