package wskim.aos.simpledutch.ui.feature.homeTab

import androidx.compose.runtime.MutableState
import wskim.aos.simpledutch.common.base.BaseViewEvent
import wskim.aos.simpledutch.common.base.BaseViewSideEffect
import wskim.aos.simpledutch.common.base.BaseViewState
import wskim.aos.simpledutch.common.base.SdV1ScreenStateEnum
import wskim.aos.simpledutch.progaurdSafeZone.HomeDutchListItem


class HomeContract {
    sealed class Event : BaseViewEvent {
        object HomeWriteButtonClicked : Event()
    }

    data class State(
        override val screenState: MutableState<SdV1ScreenStateEnum>,
        val list: MutableList<HomeDutchListItem>
    ) : BaseViewState

    sealed class Effect : BaseViewSideEffect {
        sealed class Navigation : Effect() {
            object GoToHomeWrite : Navigation()
        }
    }
}