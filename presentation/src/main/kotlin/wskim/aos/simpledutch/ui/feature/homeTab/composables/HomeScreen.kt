package wskim.aos.simpledutch.ui.feature.homeTab.composables

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.Flow
import wskim.aos.simpledutch.common.base.BaseScreen
import wskim.aos.simpledutch.common.base.SIDE_EFFECTS_KEY
import wskim.aos.simpledutch.common.base.SdV1ScreenStateEnum
import wskim.aos.domain.utils.CharFormatUtils
import wskim.aos.domain.proguardSafeZone.vo.DutchListItemVO
import wskim.aos.simpledutch.ui.feature.homeTab.HomeContract
import wskim.aos.simpledutch.ui.theme.Blue
import wskim.aos.simpledutch.ui.theme.Gray

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {

    val list = arrayListOf<DutchListItemVO>()
    repeat(3) {
        list.add(DutchListItemVO(title = "${it}차", amount = (1000 * it).toString(), enterPersonList = arrayListOf()))
    }


    HomeScreen(
        state = HomeContract.State(
            screenState = remember { mutableStateOf(SdV1ScreenStateEnum.SUCCESS) },
            totalAmount = remember { mutableIntStateOf(100000) },
            list = list.toMutableStateList()
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
    val context = LocalContext.current
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        onEventSent(HomeContract.Event.OnResume)

        effectFlow?.collect { effect ->
            when (effect) {
                is HomeContract.Effect.Toast -> homeScreenToast(context, effect)
                is HomeContract.Effect.Navigation -> onNavigationRequested(effect)
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
                Text(
                    text = "전체 금액: ${CharFormatUtils.amount(state.totalAmount.value)}",
                    fontSize = 30.sp
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 20.dp)
            ) {
                Button(
                    onClick = { onEventSent(HomeContract.Event.HomeWriteButtonClicked) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(end = 10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Blue)
                ) {
                    Box(
                        modifier = Modifier.height(30.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "추가하기", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Button(
                    onClick = { onEventSent(HomeContract.Event.HomeEndButtonClicked) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Gray)
                ) {
                    Box(
                        modifier = Modifier.height(30.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "정산하기", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            HomeList(state.list)
        }
    )
}

fun homeScreenToast(context: Context, effect: HomeContract.Effect.Toast) {
    when (effect) {
        is HomeContract.Effect.Toast.ShowListEmpty -> {
            Toast.makeText(context, "더치 페이 이력을 먼저 추가 해주세요", Toast.LENGTH_SHORT).show()
        }
    }
}