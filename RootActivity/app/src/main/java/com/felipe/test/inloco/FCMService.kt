package com.felipe.test.inloco

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.inlocomedia.android.engagement.InLocoEngagement
import com.inlocomedia.android.engagement.request.FirebasePushProvider

class FCMService : FirebaseMessagingService() {

    private val gson = Gson()

    override fun onMessageReceived(message: RemoteMessage) {
        val data = gson.fromJson(message.data["in_loco_data"], PushInformaion::class.java)
        createNotificationChannel("inLoco")

        // Create an explicit intent for an Activity in your app
        val intent = Intent(this, RootActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(data.content.title)
            .setContentText(data.content.message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(1, builder.build())
        }

    }

    override fun onNewToken(firebaseToken: String) {
        if (firebaseToken.isNotEmpty()) {
            val pushProvider = FirebasePushProvider.Builder ()
                .setFirebaseToken(firebaseToken)
                .build()
            InLocoEngagement.setPushProvider(this, pushProvider);
        }
    }

    private fun createNotificationChannel(channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = channelName
            val descriptionText = ""
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        private const val CHANNEL_ID = "general"
    }

    class PushInformaion(
        val id: String,
        val content:PushInformaionContent
    )

    class PushInformaionContent(
        val title: String,
        val message: String,
        @SerializedName("icon_url") val iconUrl: String
    )
}
