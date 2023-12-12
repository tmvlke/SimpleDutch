package wskim.aos.simpledutch.core

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import wskim.aos.simpledutch.progaurdSafeZone.HomeDutchListItemVO
import java.util.ArrayList

class SharedPreferencesManager(context: Context) : BaseSharedPreferencesManager(context) {

    fun clearDefaultPrefs() = privateAppPrefs.edit().clear().apply()

    fun saveDutchInfo(homeDutchListItemVO: HomeDutchListItemVO) {
        putValueToPrivateAppPrefs(
            Gson().toJson(
                getDutchInfoList().apply {
                    add(homeDutchListItemVO)
                }
            ),
            SP_HOME_DUTCH_LIST_ITEM
        )
    }

    fun getDutchInfoList(): ArrayList<HomeDutchListItemVO> {
        val type = object : TypeToken<ArrayList<HomeDutchListItemVO>>() {}.type
        return privateAppPrefs.getString(SP_HOME_DUTCH_LIST_ITEM, "")?.let {
            Gson().fromJson(it, type)
        }?: arrayListOf()
    }
}