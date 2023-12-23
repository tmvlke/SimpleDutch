package wskim.aos.simpledutch.common.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import wskim.aos.simpledutch.common.compose.PopupContainer

@Composable
fun BasePopup(
    popup: @Composable (popup: BasePopup) -> Unit = {},
    popupShowRule: MutableList<BasePopupVo> = arrayListOf(),
) {
    var indexPopup: Int = -1
    popupShowRule.forEachIndexed { index, value ->
        if (value.isShow) {
            indexPopup = index
            return@forEachIndexed
        }
    }

    if (popupShowRule.isNotEmpty() && indexPopup >= 0) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            PopupContainer(
                onChangeState = {
                    popupShowRule[indexPopup].backgroundClickAction.invoke()
                },
                dialogContent = {
                    popup.invoke(popupShowRule[indexPopup].enum)
                }
            )
        }
    }
}