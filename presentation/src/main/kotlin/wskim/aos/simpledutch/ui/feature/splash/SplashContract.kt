package wskim.aos.simpledutch.ui.feature.splash

import androidx.compose.runtime.MutableState
import wskim.aos.simpledutch.common.base.BaseViewEvent
import wskim.aos.simpledutch.common.base.BaseViewSideEffect
import wskim.aos.simpledutch.common.base.BaseViewState
import wskim.aos.simpledutch.common.base.SdV1ScreenStateEnum


class SplashContract {

    sealed class Event : BaseViewEvent {
        object BackButtonClicked : Event()
    }

    data class State(
        override val screenState: MutableState<SdV1ScreenStateEnum>,
    ) : BaseViewState

    sealed class Effect : BaseViewSideEffect {

        sealed class Navigation : Effect() {
            object GoToMain : Navigation()
        }
    }
}
