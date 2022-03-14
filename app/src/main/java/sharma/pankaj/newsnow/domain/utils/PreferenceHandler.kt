package sharma.pankaj.newsnow.domain.utils

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceHandler @Inject constructor(private val context: Context) {

    private val PREF_NAME = "pref_news"
    private val MODE = Context.MODE_PRIVATE

    fun writeBoolean(key: String, value: Boolean) {
        getEditor(context).putBoolean(key, value).commit()
    }

    fun readBoolean(
        key: String,
        defValue: Boolean
    ): Boolean {
        return getPreferences(context).getBoolean(key, defValue)
    }

    fun writeInteger(key: String, value: Int) {
        getEditor(context).putInt(key, value).commit()
    }

    fun readInteger(key: String, defValue: Int): Int {
        return getPreferences(context).getInt(key, defValue)
    }

    fun writeString(key: String, value: String) {
        getEditor(context).putString(key, value).commit()
    }

    fun readString(key: String, defValue: String): String {
        return getPreferences(context).getString(key, defValue)!!
    }

    fun writeFloat(key: String, value: Float) {
        getEditor(context).putFloat(key, value).commit()
    }

    fun readFloat(key: String, defValue: Float): Float {
        return getPreferences(context).getFloat(key, defValue)
    }

    fun writeLong(key: String, value: Long) {
        getEditor(context).putLong(key, value).commit()
    }

    fun readLong(key: String, defValue: Long): Long {
        return getPreferences(context).getLong(key, defValue)
    }

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, MODE)
    }

    private fun getEditor(context: Context): SharedPreferences.Editor {
        return getPreferences(context).edit()
    }

    fun clearSharePreferences(context: Context) {
        try {
            val preferences = context.getSharedPreferences(PREF_NAME, MODE)
            preferences.edit().clear().apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun remove(key: String) {
        getPreferences(context).edit().remove(key).apply()
    }

}