package wskim.aos.simpledutch.ui.feature.homeTab.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.flow.Flow
import wskim.aos.simpledutch.ui.feature.homeTab.HomeContract
import wskim.aos.simpledutch.common.base.BaseScreen
import wskim.aos.simpledutch.common.base.SIDE_EFFECTS_KEY
import wskim.aos.simpledutch.common.base.SdV1ScreenStateEnum

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {

    HomeScreen(
        state = HomeContract.State(
            screenState = remember { mutableStateOf(SdV1ScreenStateEnum.SUCCESS) },
        ),
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {},
    )
}

@Composable
fun HomeScreen(
    state: HomeContract.State,
    effectFlow: Flow<HomeContract.Effect>?,
    onEventSent: (event: HomeContract.Event) -> Unit,
    onNavigationRequested: (HomeContract.Effect.Navigation) -> Unit
) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.collect { effect ->
            when (effect) {
                is HomeContract.Effect.Navigation -> {
                    onNavigationRequested(effect)
                }
            }
        }
    }

    BaseScreen(
        screenState = state.screenState,
        body = {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val (text) = createRefs()

                Text(text = "홈", modifier = Modifier.constrainAs(text) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                })
            }
        })
}