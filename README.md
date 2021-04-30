# Android Alarm

A ideia desse projeto é criar um aplicativo exemplo que avise o usuário em uma hora específica. Ou seja, um alarme. 

## Detalhes do projeto:
1) Projeto desenvolvido em Kotlin
1) Utilização de viewBinding e dataBinding
1) O alarme é programado utilizando o <a href="https://developer.android.com/reference/android/app/AlarmManager">AlarmManager</a>.

## Modo Soneca 

A maior dificuldade encontrada durante esse projeto chama-se modo soneca. Trata-se de uma otimização de bateria que faz com que, em certas ocasiões, o dispositivo durma. Quando fica nesse estado, várias funcionalidades são desabilitadas, inclusive os alarmes do alarm manager. Para contornar este problema, foi utilizado o método setAlarmClock() do AlarmManager.

Para ler mais sobre o modo soneca, <a href="https://developer.android.com/training/monitoring-device-state/doze-standby">clique aqui</a>.

Para testar o modo Soneca(doze) podemos utilizar comandos do adb: 


```
# Forçar o modo Soneca 
adb shell dumpsys deviceidle force-idl 

# Parar de forçar o modo Soneca
adb shell dumpsys deviceidle unforce
```
## O que aprender com esse projeto?
Está começando com o android? 

Além do alarme e o modo soneca, pode-se utilizar este projeto para estudar:

1. Activities, e seu ciclo de vida (onCreate, startActivityForResult, onActivityResult). Mais informações. <a href="https://developer.android.com/guide/components/activities/activity-lifecycle">Documentação oficial</a> 
1. Persistência em SharedPreferences. <a href="https://developer.android.com/reference/android/content/SharedPreferences">Documentação oficial</a>
1. Como mostrar e cancelar uma notificação. <a href="https://developer.android.com/training/notify-user/build-notification">Documentação oficial</a>