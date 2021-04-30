package com.example.androidalarm

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidalarm.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TIME_PICKER_ACTIVITY_ID = 1234
        private const val NO_ALARM_TEXT = "Nenhum alarme agendado"
        private const val SHARED_PREFERENCE_NAME = "AlarmText"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.favoriteSchedule1.setOnClickListener {
            scheduleAlarm(5)
        }

        binding.favoriteSchedule2.setOnClickListener {
            scheduleAlarm(30)
        }

        binding.chooseTime.setOnClickListener {
            startActivityForResult(
                Intent(this, TimePickerActivity::class.java),
                TIME_PICKER_ACTIVITY_ID
            )
        }

        binding.cancelButton.setOnClickListener {
            cancelAlarms()
        }

        reloadPreference()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TIME_PICKER_ACTIVITY_ID) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    manageTimePickerResult(data)
                }
            }
        }
    }

    private fun manageTimePickerResult(data: Intent) {
        val hour = data.getIntExtra(TimePickerActivity.RESULT_HOUR, -1)
        val minute = data.getIntExtra(TimePickerActivity.RESULT_MINUTE, -1)

        if (hour == -1 || minute == -1) {
            Toast.makeText(
                this,
                "Ops! Isso é vergonhoso! Não consegui ler os dados que eu mesmo passei! #chatiado",
                Toast.LENGTH_LONG
            ).show()
            return
        }
        scheduleAlarm(TimeConverter().toSeconds(hour, minute, 0))
    }

    private fun scheduleAlarm(seconds: Long) {
        val alarm = Calendar.getInstance()
        alarm.add(Calendar.SECOND, seconds.toInt())
        val time = SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(alarm.time)
        AlarmHelper().startAlarmManager(this, seconds)
        val text = "Alarme programado para as $time"
        binding.alarmScheduledTo.text = text
        saveAlarmText(text)
    }

    private fun saveAlarmText(text: String) {
        SharedPreferenceDAO().saveString(this, text, SHARED_PREFERENCE_NAME)
    }

    private fun cancelAlarms() {
        AlarmPlayer.instance.stop()
        setText(NO_ALARM_TEXT)
        saveAlarmText(NO_ALARM_TEXT)
        NotificationHelper().dismissNotification(this)
        AlarmHelper().stopAlarmManager(this)
    }

    private fun reloadPreference() {
        setText(
            SharedPreferenceDAO().restoreString(
                this,
                SHARED_PREFERENCE_NAME
            )
        )
    }

    private fun setText(text: String?) {
        if (!TextUtils.isEmpty(text)) {
            binding.alarmScheduledTo.text = text
        }
    }
}