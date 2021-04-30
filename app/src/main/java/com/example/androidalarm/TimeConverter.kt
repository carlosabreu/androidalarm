package com.example.androidalarm

import java.util.*

class TimeConverter {

    // Recebe uma hora, minuto e segundo e calcula quantos segundos falta para chegar neste horário.
    // Se o horário já passou, calcula para o dia seguinte
    // Ignora millisegundos
    fun toSeconds(hour: Int, minutes: Int, seconds: Int): Long {
        val time = Calendar.getInstance()
        time.set(Calendar.HOUR_OF_DAY, hour)
        time.set(Calendar.MINUTE, minutes)
        time.set(Calendar.SECOND, seconds)
        time.set(Calendar.MILLISECOND, 0)

        val now = Calendar.getInstance()
        now.set(Calendar.MILLISECOND, 0)

        if (time.before(now)) {
            time.add(Calendar.DAY_OF_MONTH, 1)
        }

        return (time.timeInMillis - now.timeInMillis) / 1000
    }
}