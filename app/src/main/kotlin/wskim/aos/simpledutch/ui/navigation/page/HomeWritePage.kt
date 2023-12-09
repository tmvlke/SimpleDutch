package wskim.aos.simpledutch.ui.navigation.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import wskim.aos.simpledutch.ui.feature.homeInsert.HomeWriteContract
import wskim.aos.simpledutch.ui.feature.homeInsert.HomeWriteViewModel
import wskim.aos.simpledutch.ui.feature.homeInsert.composables.HomeWriteScreen
import wskim.aos.simpledutch.ui.navigation.PageMoveActions

@Composable
fun HomeWritePage(actions: PageMoveActions, navBackStackEntry: NavBackStackEntry) {

    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val viewModel: HomeWriteViewModel = viewModel(
        factory = HiltViewModelFactory(context, navBackStackEntry)
    )

    HomeWriteScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            coroutineScope.launch {

                when (navigationEffect) {
                    is HomeWriteContract.Effect.Navigation.GoToBack -> actions.upPress.invoke()
                }
            }
        }
    )
}