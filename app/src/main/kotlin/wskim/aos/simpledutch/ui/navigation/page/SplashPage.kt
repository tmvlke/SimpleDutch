package wskim.aos.simpledutch.ui.navigation.page

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import wskim.aos.simpledutch.ui.feature.splash.SplashContract
import wskim.aos.simpledutch.ui.feature.splash.SplashViewModel
import wskim.aos.simpledutch.ui.feature.splash.composables.SplashScreen
import wskim.aos.simpledutch.ui.navigation.PageMoveActions

@Composable
fun SplashPage(actions: PageMoveActions, navBackStackEntry: NavBackStackEntry) {

    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val viewModel: SplashViewModel = viewModel(
        factory = HiltViewModelFactory(context, navBackStackEntry)
    )

    // 뒤로 가기 막기
    BackHandler {}

    SplashScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            coroutineScope.launch {
                delay(1000 * 1) // 스플래시 기본 딜레이 시간 적용
                when (navigationEffect) {
                    is SplashContract.Effect.Navigation.GoToMain -> actions.gotoMain.invoke()
                }
            }
        }
    )
}