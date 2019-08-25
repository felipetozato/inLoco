package com.felipe.test.inloco

import com.google.firebase.messaging.FirebaseMessagingService
import com.inlocomedia.android.engagement.InLocoEngagement
import com.inlocomedia.android.engagement.request.FirebasePushProvider

class FCMService : FirebaseMessagingService() {

    override fun onNewToken(firebaseToken: String) {
        if (firebaseToken.isNotEmpty()) {
            val pushProvider = FirebasePushProvider.Builder ()
                .setFirebaseToken(firebaseToken)
                .build()
            InLocoEngagement.setPushProvider(this, pushProvider);
        }
    }
}