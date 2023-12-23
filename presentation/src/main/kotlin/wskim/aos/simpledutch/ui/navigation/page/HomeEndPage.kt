package wskim.aos.simpledutch.ui.navigation.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import wskim.aos.simpledutch.ui.feature.homeEnd.HomeEndContract
import wskim.aos.simpledutch.ui.feature.homeEnd.HomeEndViewModel
import wskim.aos.simpledutch.ui.feature.homeEnd.composables.HomeEndScreen
import wskim.aos.simpledutch.ui.navigation.PageMoveActions

@Composable
fun HomeEndPage(actions: PageMoveActions, navBackStackEntry: NavBackStackEntry) {

    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val viewModel: HomeEndViewModel = viewModel(
        factory = HiltViewModelFactory(context, navBackStackEntry)
    )

    HomeEndScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            coroutineScope.launch {

                when (navigationEffect) {
                    is HomeEndContract.Effect.Navigation.GoToBack -> actions.upPress.invoke()
                }
            }
        }
    )
}