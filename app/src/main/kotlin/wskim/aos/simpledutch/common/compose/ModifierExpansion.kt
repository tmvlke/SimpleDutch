package wskim.aos.simpledutch.common.compose

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag

/**
 * 더블 클릭 막는 확장 함수
 * 클릭 시 발생하는 애니메이션을 제거한 버전입니다.
 */
fun Modifier.singleClick(onClick: () -> Unit): Modifier = composed (
    inspectorInfo = {
        name = "singleClick"
        value = onClick
    }
) {
    var sec by remember { mutableLongStateOf(0L) }

    Modifier.clickable {
        if ((System.currentTimeMillis() - sec) > 800L) {
            sec = System.currentTimeMillis()
            onClick()
        }
    }
}

fun Modifier.addFocusCleaner(focusManager: FocusManager, doOnClear: () -> Unit = {}): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            doOnClear()
            focusManager.clearFocus()
        })
    }
}

fun Modifier.bindId(@StringRes id: Int? = null): Modifier {
    return this.testTag(id.toString())
}