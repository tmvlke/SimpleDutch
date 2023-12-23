package wskim.aos.simpledutch.ui.feature.main

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import wskim.aos.simpledutch.common.base.SdV1ScreenStateEnum
import wskim.aos.simpledutch.common.base.SdV1ViewModel
import wskim.aos.simpledutch.ui.navigation.MainTabEnum
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
//    private val myPageUseCase: MyPageUseCase,
) : SdV1ViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {

    init {
        super.onCreate(
            initialData = {
                setInitialData()
            },
            initialHandleUiMonitoring = {
                handleUiMonitoring()
            }
        )
    }

    override fun setInitialState(): MainContract.State = MainContract.State(
        screenState = mutableStateOf(SdV1ScreenStateEnum.SUCCESS),
        openTab = mutableStateOf(MainTabEnum.Home),
    )

    override suspend fun setInitialData() {
        moveMainTab(MainTabEnum.Home)
    }

    override fun handleUiMonitoring() {
    }


    override fun handleEvents(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.MainTabSelection -> moveMainTab(event.type)
        }
    }

    private fun moveMainTab(tabType : MainTabEnum) {
        setState { copy(openTab = mutableStateOf(tabType)) }
        setEffect { MainContract.Effect.MoveMainTab(tabType) }
    }
}