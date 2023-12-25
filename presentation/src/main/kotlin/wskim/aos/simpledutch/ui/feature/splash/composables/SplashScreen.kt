package wskim.aos.simpledutch.ui.feature.splash.composables

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.Flow
import wskim.aos.simpledutch.common.base.BaseScreen
import wskim.aos.simpledutch.common.base.SIDE_EFFECTS_KEY
import wskim.aos.simpledutch.common.base.SdV1ScreenStateEnum
import wskim.aos.simpledutch.ui.feature.splash.SplashContract

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        state = SplashContract.State(
            screenState = remember { mutableStateOf(SdV1ScreenStateEnum.COMPLETE) },
        ),
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {}
    )
}

@Composable
fun SplashScreen(
    state: SplashContract.State,
    effectFlow: Flow<SplashContract.Effect>?,
    onEventSent: (event: SplashContract.Event) -> Unit,
    onNavigationRequested: (SplashContract.Effect.Navigation) -> Unit
) {
    // 뒤로 가기 막기
    BackHandler {}

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.collect { effect ->
            when (effect) {

                is SplashContract.Effect.Navigation -> {
                    onNavigationRequested(effect)
                }
            }
        }
    }

    BaseScreen(
        screenState = remember { mutableStateOf(SdV1ScreenStateEnum.COMPLETE) },
        body = {
            SplashImage()
        }
    )
}