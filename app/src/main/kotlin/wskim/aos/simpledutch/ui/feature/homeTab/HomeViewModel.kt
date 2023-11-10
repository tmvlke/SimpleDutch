package wskim.aos.simpledutch.ui.feature.homeTab

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import wskim.aos.simpledutch.common.base.SdV1ScreenStateEnum
import wskim.aos.simpledutch.common.base.SdV1ViewModel
import wskim.aos.simpledutch.core.bl.useCase.UserInfoUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase,
) : SdV1ViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {

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

    override fun setInitialState(): HomeContract.State = HomeContract.State(
        screenState = mutableStateOf(SdV1ScreenStateEnum.SUCCESS)
    )

    override suspend fun setInitialData() {
        // 데이터 추가
    }

    override fun handleUiMonitoring() {}

    override fun handleEvents(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.SignUpButtonSelection -> setEffect {
                HomeContract.Effect.Navigation.GoToSignUp
            }

            is HomeContract.Event.SignInButtonSelection -> setEffect {
                HomeContract.Effect.Navigation.GoToSignIn
            }
        }
    }


}