package wskim.aos.simpledutch.ui.feature.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import wskim.aos.simpledutch.common.base.SdV1ViewModel
import wskim.aos.simpledutch.common.base.SdV1ScreenStateEnum
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
//    private val userInfoManageUseCase: UserInfoManageUseCase
) : SdV1ViewModel<SplashContract.Event, SplashContract.State, SplashContract.Effect>() {

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

    override fun setInitialState(): SplashContract.State = SplashContract.State(
        screenState = mutableStateOf(SdV1ScreenStateEnum.COMPLETE),
    )

    override suspend fun setInitialData() {}

    override fun handleUiMonitoring() {
        gotoMain()
    }

    override fun handleEvents(event: SplashContract.Event) {
        when (event) {
            is SplashContract.Event.BackButtonClicked -> {}
        }
    }

    private fun gotoMain() {
        viewModelScope.launch {
            // 1초 대기
            delay(1000L)

            // 메인으로 이동
            setEffect { SplashContract.Effect.Navigation.GoToMain }
        }
    }
}