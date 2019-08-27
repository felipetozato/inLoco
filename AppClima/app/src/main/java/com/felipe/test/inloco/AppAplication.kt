package com.felipe.test.inloco

import android.app.Application
import android.content.Context
import com.felipe.test.inloco.di.DaggerComponentProvider
import com.felipe.test.inloco.di.DaggerInLocoAppComponent
import com.felipe.test.inloco.di.InLocoAppComponent
import com.inlocomedia.android.engagement.InLocoEngagement
import com.inlocomedia.android.engagement.InLocoEngagementOptions
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener
import com.google.android.gms.ads.MobileAds
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.inlocomedia.android.engagement.request.FirebasePushProvider


class AppAplication: Application(), DaggerComponentProvider {


    override val component: InLocoAppComponent by lazy {
        DaggerInLocoAppComponent.builder()
            .applicationContext(applicationContext)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        initInAppLoco(this)

        MobileAds.initialize(this) { }

    }

    private fun initInAppLoco(context: Context) {
        // Set initialization options
        val options = InLocoEngagementOptions.getInstance(context)

        // The App ID you obtained in the dashboard
        options.applicationId = "aa37443a-8930-4dff-b241-0cfc24a6108d"

        // Verbose mode; enables SDK logging, defaults to true.
        // Remember to set to false in production builds.
        options.isLogEnabled = true

        //Initialize the SDK
        InLocoEngagement.init(context, options)

        getToken()
    }

    private fun getToken() {
        // Retrieve the Firebase token
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.d("TAG", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                // Log and toast
                Log.d("TAG", token)
                if (token != null && token.isNotEmpty()) {
                    val pushProvider = FirebasePushProvider.Builder()
                        .setFirebaseToken(token)
                        .build()
                    InLocoEngagement.setPushProvider(this, pushProvider)
                }
            })
    }
}