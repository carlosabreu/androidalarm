package com.example.androidalarm

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidalarm.databinding.ActivityTimePickerBinding

/**
 * Activity que apresenta o relógio para selecionar a hora do alarme
 */
class TimePickerActivity : AppCompatActivity() {
    companion object {
        const val RESULT_HOUR = "hour"
        const val RESULT_MINUTE = "minute"
    }

    private lateinit var binding: ActivityTimePickerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimePickerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.okButton.setOnClickListener {
            val resultData = Intent()
            resultData.putExtra(RESULT_HOUR, binding.timePicker.hour)
            resultData.putExtra(RESULT_MINUTE, binding.timePicker.minute)
            setResult(Activity.RESULT_OK, resultData)
            finish()
        }
        binding.cancelButton.setOnClickListener {
            finish()
        }
    }
}