package wskim.aos.simpledutch.ui.feature.splash

import androidx.compose.runtime.MutableState
import wskim.aos.baseuikit.base.BaseViewEvent
import wskim.aos.baseuikit.base.BaseViewSideEffect
import wskim.aos.baseuikit.base.BaseViewState
import wskim.aos.baseuikit.base.SdV1ScreenStateEnum


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
