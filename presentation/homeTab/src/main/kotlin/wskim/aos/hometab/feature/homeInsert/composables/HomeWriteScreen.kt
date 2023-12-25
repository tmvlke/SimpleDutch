package wskim.aos.hometab.feature.homeInsert.composables

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import wskim.aos.baseuikit.base.BaseScreen
import wskim.aos.baseuikit.base.SIDE_EFFECTS_KEY
import wskim.aos.baseuikit.base.SdV1ScreenStateEnum
import wskim.aos.baseuikit.compose.AppBar
import wskim.aos.baseuikit.compose.transformation.AmountTransformation
import wskim.aos.domain.proguardSafeZone.vo.DutchListItemVO
import wskim.aos.hometab.feature.homeInsert.HomeWriteContract
import wskim.aos.baseuikit.theme.Blue
import wskim.aos.baseuikit.theme.Gray
import wskim.aos.baseuikit.theme.LightGray

@Preview(showBackground = true)
@Composable
fun HomeWriteScreenPreview() {

    val list = arrayListOf<DutchListItemVO>()
    repeat(3) {
        list.add(DutchListItemVO(title = "${it}차", amount = (1000 * it).toString(), enterPersonList = arrayListOf()))
    }

    HomeWriteScreen(
        state = HomeWriteContract.State(
            screenState = remember { mutableStateOf(SdV1ScreenStateEnum.COMPLETE) },
            title = remember { MutableStateFlow("") },
            amount = remember { MutableStateFlow("") },
            enterPersonName = remember { mutableStateOf("") },
            enterPersonList = remember { mutableStateListOf() },
            completeButtonEnabled = remember { mutableStateOf(false) }
        ),
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {},
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeWriteScreen(
    state: HomeWriteContract.State,
    effectFlow: Flow<HomeWriteContract.Effect>?,
    onEventSent: (event: HomeWriteContract.Event) -> Unit,
    onNavigationRequested: (HomeWriteContract.Effect.Navigation) -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.collect { effect ->
            when (effect) {
                is HomeWriteContract.Effect.Toast -> homeWriteScreenToast(context, effect)
                is HomeWriteContract.Effect.Navigation -> onNavigationRequested(effect)
            }
        }
    }

    BaseScreen(
        screenState = state.screenState,
        topBar = {
            AppBar(
                text = "더치 페이 추가하기",
                startImageClickAction = { onEventSent(HomeWriteContract.Event.BackButtonClicked) }
            )
        },
        body = {
            TextField(
                value = state.title.collectAsState().value,
                onValueChange = { state.title.value = it },
                label = {
                    Text(text = "지출 내용")
                },
                placeholder = {
                    Text(text = "1차 고기집")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
            )

            TextField(
                value = state.amount.collectAsState().value,
                onValueChange = { state.amount.value = it },
                label = {
                    Text(text = "총 가격 (원)")
                },
                placeholder = {
                    Text(text = "100,000")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                ),
                visualTransformation = AmountTransformation
            )

            TextField(
                value = state.enterPersonName.value,
                onValueChange = { state.enterPersonName.value = it },
                label = {
                    Text(text = "참여 인원 이름")
                },
                placeholder = {
                    Text(text = "사람1")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions {
                    onEventSent(HomeWriteContract.Event.SaveEnterPersonClicked)
                }
            )

            LazyColumn(
                contentPadding = PaddingValues(20.dp)
            ) {
                state.enterPersonList.size.also {
                    if (it > 0) {
                        item {
                            Text(
                                text = "총 ${state.enterPersonList.size}명",
                                modifier = Modifier.padding(start = 5.dp, bottom = 10.dp)
                            )
                        }
                    }
                }

                itemsIndexed(state.enterPersonList) { position, name ->
                    Button(
                        onClick = {
                            onEventSent(HomeWriteContract.Event.RemoveEnterPersonClicked(position))
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Gray)
                    ) {
                        Row {
                            Text(
                                text = name,
                                modifier = Modifier.weight(1f)
                            )
                            Image(
                                painter = painterResource(id = wskim.aos.baseuikit.R.drawable.clear),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        },
        footer = {
            Button(
                onClick = {
                    onEventSent(HomeWriteContract.Event.CompleteButtonClicked)
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
                    Text(text = "추가하기", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    )
}

fun homeWriteScreenToast(context: Context, effect: HomeWriteContract.Effect.Toast) {
    when(effect) {
        is HomeWriteContract.Effect.Toast.ShowComplete -> {
            Toast.makeText(context, "추가 되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}