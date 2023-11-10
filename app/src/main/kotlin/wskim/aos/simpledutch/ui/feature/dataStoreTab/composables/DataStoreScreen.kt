package wskim.aos.simpledutch.ui.feature.dataStoreTab.composables

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
import wskim.aos.simpledutch.common.base.BaseScreen
import wskim.aos.simpledutch.common.base.SIDE_EFFECTS_KEY
import wskim.aos.simpledutch.common.base.SdV1ScreenStateEnum
import wskim.aos.simpledutch.ui.feature.dataStoreTab.DataStoreContract

@Preview(showBackground = true)
@Composable
fun DataStoreScreenPreview() {

    DataStoreScreen(
        state = DataStoreContract.State(
            screenState = remember { mutableStateOf(SdV1ScreenStateEnum.SUCCESS) },
        ),
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {},
    )
}

@Composable
fun DataStoreScreen(
    state: DataStoreContract.State,
    effectFlow: Flow<DataStoreContract.Effect>?,
    onEventSent: (event: DataStoreContract.Event) -> Unit,
    onNavigationRequested: (DataStoreContract.Effect.Navigation) -> Unit
) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.collect { effect ->
            when (effect) {
                is DataStoreContract.Effect.Navigation -> {
                    onNavigationRequested(effect)
                }
            }
        }
    }

    BaseScreen(screenState = state.screenState, body = {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (text) = createRefs()

            Text(text = "데이터 저장소", modifier = Modifier.constrainAs(text) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            })
        }
    })
}