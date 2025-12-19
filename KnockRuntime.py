ffrom gtts import gTTS

import os

import speech_recognition as sr

import time

import vlc

from configparser import ConfigParser


import re

import requests

import firebase_admin

from firebase_admin import credentials, messaging


import threading

from openai import OpenAI


client = OpenAI(api_key="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")


cred = credentials.Certificate("serviceAccountKey.json")

firebase_admin.initialize_app(cred)


token_android = "fYO_zrBbQGCa-3jGKQ8hjp:APA91bH9Owkr3J5bbgjAhzGkjOnuU6Hbh6eHdBlKVRc08PIILtl2CkLLTmWNSm_Ppsxib-xVbW7Q5sLGlYGaZs3t5ajl9I-5kk1CGi4WdaMnyU0RhTbcqXc"


def enviar_notificacao(conteudo):

   print("A enviar notificação...")



   message = messaging.Message(

       notification=messaging.Notification(

           title="Knock - Alerta Campainha",

           body=conteudo

       ),

       token=token_android,

   )


   try:

       response = messaging.send(message)



   except Exception as e:

       print("Erro ao enviar:", e)


def luminaAi(pergunta):

   try:

       response = client.chat.completions.create(

           model="gpt-4o-mini",

           messages=[

               {"role": "system", "content": "You are an intelligent, connected doorbell device. make a summary of  what the person said. Answer in Portuguese-Portugal. Also say The person outside said (A pessoa lá fora disse:) and use quotation marks. Also ask if the user wants >

               {"role": "user", "content": pergunta}

           ]

       )


       content = response.choices[0].message.content

       print("DEBUG:" + content)


       enviar_notificacao(content)

       falar("Obrigado. Alguem ira atender a porta o mais rápido possivel. Da minha parte é tudo. Fique bem.")


   except Exception as e:

       print(e)
ativarEscrita()


def falar(text):

   tts = gTTS(text=text, lang='pt', tld='pt')

   tts.save("output.mp3")

   os.system("mpg123 -q output.mp3")


def reconhecer():

   portaalerta = "Alguem está á sua Porta!"

   enviar_notificacao(portaalerta)

   falar("Bem-Vindo. Por favor. após o sinal diga como se chama e o que pretende. Obrigada.")



   recognizer = sr.Recognizer()

   with sr.Microphone() as source:

       print("Pode Falar.")

       audio = recognizer.listen(source, phrase_time_limit=20)


   try:

       dados = recognizer.recognize_google(audio, language='pt-PT')

       luminaAi(dados)


   except:

       print("Erro")

       falar("desculpe, não percebi.")



def ativarEscrita():

   pergunta = input('FALA: \n')


   luminaAi(pergunta)



from gpiozero import Button

from signal import pause


botao = Button(17, bounce_time=0.02)


print("À espera que carregue no botão...")


def quando_carregar():

   os.system("mpg123 -q campainha.mp3")

   reconhecer()


def quando_largar():

   print("-> Botão Largado")


botao.when_pressed = quando_carregar


pause()
