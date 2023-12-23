package wskim.aos.simpledutch.common.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import wskim.aos.simpledutch.common.compose.addFocusCleaner
import wskim.aos.simpledutch.ui.theme.Purple40
import wskim.aos.simpledutch.ui.theme.WHITE

@Composable
fun BaseScreen(
    screenState: MutableState<SdV1ScreenStateEnum>,
    topBar: @Composable () -> Unit = {},
    body: @Composable () -> Unit = {},
    footer: @Composable () -> Unit = {},
    loading: @Composable (() -> Unit)? = null,
    isLoadingBackground: Boolean = false,
    isBackgroundFocusClear: Boolean = true,
    popup: @Composable (popup: BasePopup) -> Unit = {},
    popupShowRule: MutableList<BasePopupVo> = arrayListOf(),
    bottomSheet: @Composable () -> Unit = {},
) {

    Box {

        Column(
            Modifier.fillMaxSize().background(WHITE)
        ) {
            Column {
                topBar.invoke()
            }

            Box(
                modifier = Modifier.fillMaxWidth().weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    var modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)

                    if (isBackgroundFocusClear) {
                        modifier = modifier.addFocusCleaner(LocalFocusManager.current)
                    }

                    Column(
                        modifier = modifier
                    ) {
                        body.invoke()
                    }

                    footer.invoke()
                }

                if (screenState.value == SdV1ScreenStateEnum.LOADING) {

                    var modifier = Modifier.fillMaxSize()

                    if(isLoadingBackground) {
                        modifier = modifier.background(WHITE)
                    }

                    Box(
                        modifier = modifier,
                        contentAlignment = Alignment.Center,
                    ) {
                        if(loading != null) {
                            loading.invoke()
                        } else {
                            CircularProgressIndicator(
                                color = Purple40
                            )
                        }
                    }
                }
            }
        }

        // 팝업
        BasePopup(
            popup = popup,
            popupShowRule = popupShowRule
        )

        // 바텀 시트
        bottomSheet.invoke()
    }
}