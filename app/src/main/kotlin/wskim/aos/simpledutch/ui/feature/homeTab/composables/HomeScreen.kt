package wskim.aos.simpledutch.ui.feature.homeTab.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.Flow
import wskim.aos.simpledutch.common.base.BaseScreen
import wskim.aos.simpledutch.common.base.SIDE_EFFECTS_KEY
import wskim.aos.simpledutch.common.base.SdV1ScreenStateEnum
import wskim.aos.simpledutch.progaurdSafeZone.HomeDutchListItem
import wskim.aos.simpledutch.ui.feature.homeTab.HomeContract
import wskim.aos.simpledutch.ui.theme.Blue

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {

    val list = arrayListOf<HomeDutchListItem>()
    repeat(3) {
        list.add(HomeDutchListItem(name = "${it}차", price = 1000 * it, enterCount = 1 + it))
    }


    HomeScreen(
        state = HomeContract.State(
            screenState = remember { mutableStateOf(SdV1ScreenStateEnum.SUCCESS) },
            list = list
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
            // 총 가격
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "0원", fontSize = 30.sp)
            }

            Button(
                onClick = { onEventSent(HomeContract.Event.HomeWriteButtonClicked) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Blue)
            ) {
                Box(
                    modifier = Modifier.height(30.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "추가하기", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }

            HomeList(state.list)
        }
    )
}