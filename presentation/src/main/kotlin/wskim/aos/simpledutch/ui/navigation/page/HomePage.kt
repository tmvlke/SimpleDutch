package wskim.aos.simpledutch.ui.navigation.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import wskim.aos.simpledutch.ui.feature.homeTab.HomeContract
import wskim.aos.simpledutch.ui.feature.homeTab.HomeViewModel
import wskim.aos.simpledutch.ui.feature.homeTab.composables.HomeScreen
import wskim.aos.simpledutch.ui.navigation.PageMoveActions

@Composable
fun HomePage(actions: PageMoveActions, navBackStackEntry: NavBackStackEntry) {
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val viewModel: HomeViewModel = viewModel(
        factory = HiltViewModelFactory(context, navBackStackEntry)
    )

    HomeScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            coroutineScope.launch {

                when (navigationEffect) {
                    is HomeContract.Effect.Navigation.GoToHomeWrite -> actions.gotoHomeWrite.invoke()
                    is HomeContract.Effect.Navigation.GoToHomeEnd -> actions.gotoHomeEnd.invoke()
                }
            }
        }
    )
}