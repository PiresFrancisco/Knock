Knock - Smart AI DoorBell

O Knock é um sistema de campainha inteligente desenvolvido como Projeto Integrado. Ao contrário das campainhas tradicionais, o Knock utiliza Reconhecimento de Voz e a API da OpenAI (ChatGPT) para interagir com o visitante, resumir a sua intenção e enviar notificações em tempo real para uma aplicação Android dedicada.

🚀 Funcionalidades Principais

    Notificações Push Instantâneas: O utilizador é alertado no telemóvel assim que o botão é pressionado através do Firebase Cloud Messaging (FCM).

Interação por Voz Dinâmica: O sistema solicita ao visitante que se identifique e indique o motivo da visita.

Resumo Inteligente (IA): A voz do visitante é convertida em texto e processada pelo GPT-4o-mini, que gera um resumo conciso enviado ao proprietário.

Transmissão de Vídeo em Tempo Real: Visualização da entrada via protocolo RTSP com baixo atraso.

Resposta Automática: O sistema informa o visitante, por voz, que o proprietário foi notificado.

🛠️ Hardware Utilizado
Componente	Função
Raspberry Pi	

Cérebro do sistema e gestão de GPIO.

Módulo Câmara OV5647	

Captura de vídeo para streaming.

Botão Físico	

Acionamento da campainha (Ligado ao GPIO 17).

Microfone & Coluna	

Interface de áudio para interação com o visitante.

Resistência 1kOhm	

Proteção e estabilização do sinal (Pull-down).

📐 Fluxo de Funcionamento

O sistema segue uma sequência lógica para garantir que nenhuma visita passe despercebida:

    Deteção: O visitante pressiona o botão físico.

Alerta Inicial: A Raspberry Pi toca um som local e envia a primeira notificação: "Alguém está à sua porta!".

Diálogo: O sistema pede a identificação do visitante e grava o áudio.

Processamento: O áudio é convertido em texto (SpeechRecognition) e resumido pela IA.

Notificação Final: O resumo da intenção do visitante chega ao telemóvel do utilizador.

💻 Software e Tecnologias
Backend (Raspberry Pi - Python)

    Bibliotecas: gpiozero, speech_recognition, gTTS, firebase_admin, openai.

Streaming de Vídeo: MediaMTX com rpicam-vid (H.264) via RTSP.

Frontend (Android)

    Linguagem: Kotlin.

Ferramentas: Android Studio & Figma (Prototipagem).

Player de Vídeo: ExoPlayer para suporte nativo a RTSP.

⚙️ Instalação e Configuração

    Clonar o Repositório:
    Bash

git clone https://github.com/PiresFrancisco/Knock.git

Configurar Credenciais:

    Coloca o teu ficheiro serviceAccountKey.json do Firebase na pasta raiz.

Adiciona a tua API_KEY da OpenAI no script principal.

Atualiza o token_android com o token gerado pela tua App.

Executar o Sistema:
Bash

    python3 main.py

👥 Autores

    André Medina (82611) 

Francisco Pires (82604)

Professor: Fernando Barros
