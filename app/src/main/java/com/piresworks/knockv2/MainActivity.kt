package com.piresworks.knockv2

import android.content.Intent
import android.os.Bundle
import android.util.Log // <--- IMPORTANTE: Adicionado para o Log funcionar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
import com.piresworks.knockv2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ==================================================================
        // 👇 CÓDIGO ADICIONADO PARA PEGAR O TOKEN 👇
        // ==================================================================
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TOKEN_FIREBASE", "Falha ao pegar o token", task.exception)
                return@addOnCompleteListener
            }

            // Pega o novo token
            val token = task.result

            // Imprime no Logcat
            Log.d("TOKEN_FIREBASE", "------------------------------------------")
            Log.d("TOKEN_FIREBASE", token)
            Log.d("TOKEN_FIREBASE", "------------------------------------------")
        }
        // ==================================================================


        // Click listener for imageView9
        binding.imageView9.setOnClickListener {
            val intent = Intent(this, TemporealActivity::class.java)
            startActivity(intent)
        }

        binding.imageView3.setOnClickListener {
            val intent = Intent(this, NotificacoesActivity::class.java)
            startActivity(intent)
        }


        val calendar = java.util.Calendar.getInstance()
        val hour = calendar.get(java.util.Calendar.HOUR_OF_DAY)

        val greeting = when (hour) {
            in 6..11 -> "Bom Dia"
            in 12..17 -> "Boa Tarde"
            else -> "Boa Noite"
        }

        binding.textView2.text = greeting
    }
}