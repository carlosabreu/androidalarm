package com.example.androidalarm

import android.content.Context

/**
 * Created by Carlos on 12/06/2016.
 */
class SharedPreferenceDAO {
    fun saveString(context: Context, stringToSave: String, sharedPreferenceName: String?) {
        context.getSharedPreferences(
            sharedPreferenceName, Context.MODE_PRIVATE
        ).edit().putString(sharedPreferenceName, stringToSave).apply()
    }

    fun restoreString(context: Context, sharedPreferenceName: String?): String? {
        return context.getSharedPreferences(
            sharedPreferenceName,
            Context.MODE_PRIVATE
        ).getString(sharedPreferenceName, "")
    }
}