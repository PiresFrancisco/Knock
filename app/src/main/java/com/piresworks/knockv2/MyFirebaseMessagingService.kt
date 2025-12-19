package com.piresworks.knockv2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    // Este método roda quando o app recebe uma notificação
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Verifica se a mensagem tem conteúdo de notificação
        remoteMessage.notification?.let {
            mostrarNotificacao(it.title, it.body)
        }
    }

    // Este método roda quando o Token é gerado (na primeira vez que abre o app)
    override fun onNewToken(token: String) {
        Log.d("TOKEN_FIREBASE", "------------------------------------------")
        Log.d("TOKEN_FIREBASE", token) // <<--- COPIE ESTE TOKEN DO LOGCAT
        Log.d("TOKEN_FIREBASE", "------------------------------------------")
    }

    private fun mostrarNotificacao(titulo: String?, corpo: String?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val channelId = "knock_channel"
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher) // Ícone da notificação
            .setContentTitle(titulo)
            .setContentText(corpo)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Necessário para Android 8.0+ (Oreo)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Notificações Knock",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, notificationBuilder.build())
    }
}