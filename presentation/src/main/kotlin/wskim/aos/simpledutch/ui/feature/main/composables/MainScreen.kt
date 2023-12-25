package wskim.aos.simpledutch.ui.feature.main.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow
import wskim.aos.simpledutch.common.base.BaseScreen
import wskim.aos.simpledutch.common.base.SIDE_EFFECTS_KEY
import wskim.aos.simpledutch.common.base.SdV1ScreenStateEnum
import wskim.aos.simpledutch.common.compose.singleClick
import wskim.aos.simpledutch.ui.feature.main.MainContract
import wskim.aos.simpledutch.ui.navigation.MainTabEnum
import wskim.aos.simpledutch.ui.navigation.PageMoveActions
import wskim.aos.simpledutch.ui.navigation.page.DataStorePage
import wskim.aos.simpledutch.ui.navigation.page.HomePage
import wskim.aos.simpledutch.ui.theme.Blue
import wskim.aos.simpledutch.ui.theme.Gray

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {

    MainScreen(
        state = MainContract.State(
            screenState = remember { mutableStateOf(SdV1ScreenStateEnum.COMPLETE) },
            openTab = remember { mutableStateOf(MainTabEnum.Home) },
        ),
        effectFlow = null,
        onMainEventSent = {},
        onNavigationRequested = {},

        // 하위 탭 구성 용 매개 변수
        actions = PageMoveActions(NavController(LocalContext.current)),
        navBackStackEntry = null
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    state: MainContract.State,
    effectFlow: Flow<MainContract.Effect>?,
    onMainEventSent: (event: MainContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: MainContract.Effect.Navigation) -> Unit,

    // 하위 탭 구성 용 매개 변수
    actions: PageMoveActions,
    navBackStackEntry: NavBackStackEntry?
) {
    val tabs = MainTabEnum.values().toList()
    val tabPagerState = rememberPagerState(initialPage = state.openTab.value.ordinal) { tabs.size }

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.collect { effect ->
            when (effect) {
                is MainContract.Effect.MoveMainTab -> {
                    tabPagerState.scrollToPage(effect.mainTabEnum.ordinal)
                }

                is MainContract.Effect.Navigation -> onNavigationRequested(effect)
            }
        }
    }

    BaseScreen(
        screenState = remember { mutableStateOf(SdV1ScreenStateEnum.COMPLETE) },
        body = {
            Box(modifier = Modifier.fillMaxSize()) {
                HorizontalPager(
                    state = tabPagerState,
                    pageSpacing = 0.dp,
                    userScrollEnabled = false,
                    reverseLayout = false,
                    contentPadding = PaddingValues(0.dp),
                    beyondBoundsPageCount = 0,
                    pageSize = PageSize.Fill,
                    flingBehavior = PagerDefaults.flingBehavior(state = tabPagerState),
                    key = null,
                    pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                        state = tabPagerState,
                        orientation = Orientation.Horizontal
                    ),
                    pageContent = { page ->
                        if (navBackStackEntry == null) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Center
                            ) {
                                Text(
                                    text = "메인 페이지"
                                )
                            }
                            return@HorizontalPager
                        }
                        when (page) {
                            MainTabEnum.Home.ordinal -> HomePage(
                                actions,
                                navBackStackEntry
                            )

                            MainTabEnum.DATA_STORE.ordinal -> DataStorePage(
                                actions,
                                navBackStackEntry
                            )
                        }
                    }
                )

                // 하단 네비게이션 바  위의 그라데이션
                Box(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Transparent,
                                    Gray
                                )
                            )
                        )
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

        },
        footer = {
            Row(Modifier.padding(14.dp, 5.dp, 14.dp, 10.dp)) {
                tabs.forEachIndexed { index, mainTab ->
                    Box(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .weight(1f)
                            .singleClick {
                                onMainEventSent(MainContract.Event.MainTabSelection(mainTab))
                            },
                        contentAlignment = Center
                    ) {

                        Text(
                            text = mainTab.title,
                            color = if (state.openTab.value.ordinal == index) Blue else Gray,
                            fontWeight = if (state.openTab.value.ordinal == index) FontWeight.W600 else FontWeight.Normal
                        )
                    }
                }
            }
        }
    )
}