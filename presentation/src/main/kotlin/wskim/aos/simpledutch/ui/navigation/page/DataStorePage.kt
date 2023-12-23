package wskim.aos.simpledutch.ui.navigation.page

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import wskim.aos.simpledutch.ui.feature.dataStoreTab.DataStoreContract
import wskim.aos.simpledutch.ui.feature.dataStoreTab.DataStoreViewModel
import wskim.aos.simpledutch.ui.feature.dataStoreTab.composables.DataStoreScreen
import wskim.aos.simpledutch.ui.navigation.PageMoveActions

@Composable
fun DataStorePage(actions: PageMoveActions, navBackStackEntry: NavBackStackEntry) {

    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val viewModel: DataStoreViewModel = viewModel(
        factory = HiltViewModelFactory(context, navBackStackEntry)
    )

    // 뒤로 가기 막기
    BackHandler {}

    DataStoreScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            coroutineScope.launch {

                when (navigationEffect) {
                    is DataStoreContract.Effect.Navigation.GoToSignIn -> actions.gotoMain.invoke()
                    else -> {}
                }
            }
        }
    )
}