package com.piresworks.knockv2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
// Importações necessárias para o ExoPlayer
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.rtsp.RtspMediaSource
import com.google.android.exoplayer2.ui.StyledPlayerView

class TemporealActivity : AppCompatActivity() {

    // VARIÁVEIS DE ESTADO DO PLAYER
    private var player: ExoPlayer? = null
    private lateinit var playerView: StyledPlayerView

    // *** AJUSTE AQUI: ENDEREÇO RTSP DA CÂMARA ***
    // Use o IP que funciona na sua rede (o endereço do Raspberry Pi)
    private val rtspUrl = "rtsp://192.168.1.105:8554/doorbell"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Certifique-se que o layout activity_temporeal.xml tem o StyledPlayerView com id="@+id/player_view"
        setContentView(R.layout.activity_temporeal)

        // 1. Referenciar o componente de visualização (StyledPlayerView)
        playerView = findViewById(R.id.player_view)
    }

    /**
     * Inicializa o ExoPlayer e configura a fonte de mídia RTSP.
     */
    private fun initializePlayer() {
        if (player == null) {
            // 2. Cria a instância do Player
            player = ExoPlayer.Builder(this).build()
            playerView.player = player

            // 3. Define a fonte de mídia como RTSP
            val mediaSource = RtspMediaSource.Factory()
                .setForceUseRtpTcp(true) // Força o uso de TCP (mais estável para RTSP em redes domésticas)
                .createMediaSource(MediaItem.fromUri(rtspUrl))

            // 4. Prepara e inicia a reprodução
            player?.setMediaSource(mediaSource)
            player?.prepare()
            player?.play()
        }
    }

    /**
     * Libera o player para evitar vazamentos de memória e uso excessivo da câmara
     */
    private fun releasePlayer() {
        if (player != null) {
            player?.release()
            player = null
        }
    }

    // Ciclo de Vida: Inicia o player quando a Activity está visível
    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    // Ciclo de Vida: Libera o player quando a Activity não está visível
    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    // Opcional: Libera o player quando a Activity é destruída
    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }
}