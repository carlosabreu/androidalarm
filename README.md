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
