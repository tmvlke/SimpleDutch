package wskim.aos.simpledutch.core

import android.content.Context
import android.content.SharedPreferences

open class BaseSharedPreferencesManager(context: Context) {
    protected val privateAppPrefs: SharedPreferences by lazy {
        context.getSharedPreferences(PREF_DEFAULT, Context.MODE_PRIVATE)
    }

    private val privateAppPrefsEditor: SharedPreferences.Editor by lazy {
        privateAppPrefs.edit()
    }

    protected fun putValueToPrivateAppPrefs(value: Any?, key: String) = putValueToPref(privateAppPrefsEditor, value, key)
    private fun putValueToPref(prefEditor: SharedPreferences.Editor, value: Any?, key: String) {
        when (value) {
            is String -> prefEditor.putString(key, value).apply()
            is Boolean -> prefEditor.putBoolean(key, value).apply()
            is Int -> prefEditor.putInt(key, value).apply()
            is Long -> prefEditor.putLong(key, value).apply()
            is Float -> prefEditor.putFloat(key, value).apply()
        }
    }

    companion object {
        const val PREF_DEFAULT = "PREF_DEFAULT"
        const val SP_HOME_DUTCH_LIST_ITEM = "SP_HOME_DUTCH_LIST_ITEM"
    }
}