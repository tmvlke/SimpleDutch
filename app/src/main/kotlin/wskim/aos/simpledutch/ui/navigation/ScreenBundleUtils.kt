package wskim.aos.simpledutch.ui.navigation

import android.net.Uri
import android.util.Base64
import androidx.lifecycle.SavedStateHandle
import com.google.gson.Gson

object ScreenBundleUtils {
    fun <T> build(data: T): String {
        val encodedBytes = Base64.encode(Gson().toJson(data).toByteArray(), Base64.DEFAULT)
        return "/" + Uri.encode(String(encodedBytes))
    }

    const val TestCodeKey = "TestCodeKey"
    const val TestCodeValue = "TestCodeValue"
    inline fun <reified T> buildTestCode(data: T): SavedStateHandle {

        return SavedStateHandle().apply {
            set(TestCodeKey, TestCodeValue)
            set(T::class.java.simpleName, Gson().toJson(data))
        }
    }

    inline fun <reified T> parse(savedStateHandle: SavedStateHandle) : T {

        val arguments: String =
            savedStateHandle.get<String>(T::class.java.simpleName)?:""

        return if (!savedStateHandle.get<String>(TestCodeKey).isNullOrEmpty()) {
            Gson().fromJson(arguments, T::class.java)
        } else {
            val decodedBytes = Base64.decode(Uri.decode(arguments).toByteArray(), Base64.DEFAULT)

            Gson().fromJson(String(decodedBytes), T::class.java)
        }
    }
}