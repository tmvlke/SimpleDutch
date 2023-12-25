package wskim.aos.simpledutch.ui.navigation.page

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import wskim.aos.simpledutch.ui.feature.main.composables.MainScreen
import wskim.aos.simpledutch.MainActivity
import wskim.aos.simpledutch.ui.feature.main.MainContract
import wskim.aos.simpledutch.ui.feature.main.MainViewModel
import wskim.aos.baseuikit.navigation.PageMoveActions

@Composable
fun MainPage(actions: PageMoveActions, navBackStackEntry: NavBackStackEntry) {
    val context = LocalContext.current
    val viewModel: MainViewModel = viewModel(
        factory = HiltViewModelFactory(context, navBackStackEntry),
    )

    // 두번 뒤로 가기 했을때 앱 종료
    var backPressedTime = 0L
    BackHandler {

        if (System.currentTimeMillis() - backPressedTime >= 2000) {
            backPressedTime = System.currentTimeMillis()
            Toast.makeText(context, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        } else {
            if (context is MainActivity) {
                context.finish()
            }
        }
    }

    MainScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onMainEventSent = { event ->
            viewModel.setEvent(event)
        },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is MainContract.Effect.Navigation.GoToSplash -> {
                    actions.gotoSplash.invoke()
                }
            }
        },
        actions = actions,
        navBackStackEntry = navBackStackEntry
    )
}