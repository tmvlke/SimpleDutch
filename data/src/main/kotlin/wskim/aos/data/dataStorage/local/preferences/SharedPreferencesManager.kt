package wskim.aos.data.dataStorage.local.preferences

import android.content.Context
import android.net.Uri
import android.util.Base64
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import wskim.aos.domain.proguardSafeZone.vo.DutchHistoryListItemVO
import wskim.aos.domain.proguardSafeZone.vo.DutchListItemVO
import java.util.ArrayList

class SharedPreferencesManager(context: Context) : BaseSharedPreferencesManager(context) {

    // 리스트 안에 리스트가 있는 경우 json 처리가 번거롭기 때문에 문자열을 변환하기 위함
    private fun <T> encode(data: T): String {
        val encodedBytes = Base64.encode(Gson().toJson(data).toByteArray(), Base64.DEFAULT)
        return Uri.encode(String(encodedBytes))
    }

    // 리스트 안에 리스트가 있는 경우 json 처리가 번거롭기 때문에 문자열을 변환하기 위함
    private inline fun <reified T> decode(data: String): T {
        val decodedBytes = Base64.decode(Uri.decode(data).toByteArray(), Base64.DEFAULT)
        val type = object : TypeToken<T>() {}.type
        return Gson().fromJson(String(decodedBytes), type)
    }

    fun clearDefaultPrefs() = privateAppPrefs.edit().clear().apply()

    fun saveDutchInfo(list: ArrayList<DutchListItemVO>) {
        putValueToPrivateAppPrefs(encode(list), SP_HOME_DUTCH_LIST_ITEM)
    }

    fun getDutchInfoList(): ArrayList<DutchListItemVO> {
        return privateAppPrefs.getString(SP_HOME_DUTCH_LIST_ITEM, "")?.let {
            decode(it)
        }?: arrayListOf()
    }

    fun saveDutchHistoryInfo(list: ArrayList<DutchHistoryListItemVO>) {
        putValueToPrivateAppPrefs(encode(list), SP_HOME_DUTCH_HISTORY_LIST_ITEM)
    }

    fun getDutchHistoryInfoList(): ArrayList<DutchHistoryListItemVO> {
        return privateAppPrefs.getString(SP_HOME_DUTCH_HISTORY_LIST_ITEM, "")?.let {
            decode(it)
        }?: arrayListOf()
    }
}