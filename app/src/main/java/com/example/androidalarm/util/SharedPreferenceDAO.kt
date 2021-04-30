package com.example.androidalarm.util

import android.content.Context
import android.text.TextUtils
import java.io.IOException

/**
 * Created by Carlos on 12/06/2016.
 */
class SharedPreferenceDAO<T> {
    @Throws(IOException::class)
    fun save(context: Context, objectToSave: T, sharedPreferenceName: String?) {
        val jsonFactory = JsonFactory<T>()
        val editor = context.getSharedPreferences(
            sharedPreferenceName, Context.MODE_PRIVATE
        ).edit()
        editor.putString(sharedPreferenceName, jsonFactory.objectToJson(objectToSave))
        editor.apply()
    }

    @Throws(IOException::class)
    fun restoreAsObject(context: Context, sharedPreferenceName: String?, classe: Class<T>?): T? {
        val prefs = context.getSharedPreferences(
            sharedPreferenceName,
            Context.MODE_PRIVATE
        )
        val jSonSaved = prefs.getString(sharedPreferenceName, "")
        return if (!TextUtils.isEmpty(jSonSaved)) {
            JsonParser<T>().parse(jSonSaved, classe)
        } else null
    }
}