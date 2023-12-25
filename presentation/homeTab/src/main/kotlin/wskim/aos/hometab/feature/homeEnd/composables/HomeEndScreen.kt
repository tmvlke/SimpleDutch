package wskim.aos.hometab.feature.homeEnd.composables

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.Flow
import wskim.aos.baseuikit.base.BaseScreen
import wskim.aos.baseuikit.base.SIDE_EFFECTS_KEY
import wskim.aos.baseuikit.base.SdV1ScreenStateEnum
import wskim.aos.baseuikit.compose.AppBar
import wskim.aos.domain.utils.CharFormatUtils
import wskim.aos.domain.proguardSafeZone.vo.DutchListItemVO
import wskim.aos.domain.proguardSafeZone.vo.buildDutchEndListItemVOPreview
import wskim.aos.hometab.feature.homeEnd.HomeEndContract
import wskim.aos.baseuikit.theme.Blue
import wskim.aos.baseuikit.theme.LightGray

@Preview(showBackground = true)
@Composable
fun HomeEndScreenPreview() {

    val list = arrayListOf<DutchListItemVO>()
    repeat(3) {
        list.add(DutchListItemVO(title = "${it}차", amount = (1000 * it).toString(), enterPersonList = arrayListOf()))
    }

    HomeEndScreen(
        state = HomeEndContract.State(
            screenState = remember { mutableStateOf(SdV1ScreenStateEnum.COMPLETE) },
            totalAmount = remember { mutableIntStateOf(100000) },
            enterPersonList = remember { mutableStateListOf(buildDutchEndListItemVOPreview()) },
            completeButtonEnabled = remember { mutableStateOf(false) }
        ),
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {},
    )
}

@Composable
fun HomeEndScreen(
    state: HomeEndContract.State,
    effectFlow: Flow<HomeEndContract.Effect>?,
    onEventSent: (event: HomeEndContract.Event) -> Unit,
    onNavigationRequested: (HomeEndContract.Effect.Navigation) -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.collect { effect ->
            when (effect) {
                is HomeEndContract.Effect.Toast -> homeWriteScreenToast(context, effect)
                is HomeEndContract.Effect.Navigation -> onNavigationRequested(effect)
            }
        }
    }

    BaseScreen(
        screenState = state.screenState,
        topBar = {
            AppBar(
                text = "더치 페이 정산하기",
                startImageClickAction = { onEventSent(HomeEndContract.Event.BackButtonClicked) }
            )
        },
        body = {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "전체 금액: ${CharFormatUtils.amount(state.totalAmount.value)}", fontSize = 30.sp
                )
            }

            LazyColumn(
                contentPadding = PaddingValues(20.dp)
            ) {
                state.enterPersonList.size.also {
                    if (it > 0) {
                        item {
                            Text(
                                text = "전체 참여 인원: ${state.enterPersonList.size}명",
                                modifier = Modifier.padding(start = 5.dp, bottom = 10.dp)
                            )
                        }
                    }
                }

                itemsIndexed(state.enterPersonList) { index, item ->
                    HomeEndCard(item) {
                        onEventSent(HomeEndContract.Event.EndButtonClicked(index))
                    }
                }
            }
        },
        footer = {
            Button(
                onClick = {
                    onEventSent(HomeEndContract.Event.CompleteButtonClicked)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (state.completeButtonEnabled.value) Blue else LightGray
                )
            ) {
                Box(
                    modifier = Modifier.height(30.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "전체 정산 완료하기", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    )
}

fun homeWriteScreenToast(context: Context, effect: HomeEndContract.Effect.Toast) {
    when(effect) {
        is HomeEndContract.Effect.Toast.ShowComplete -> {
            Toast.makeText(context, "추가 되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}