package wskim.aos.simpledutch.ui.feature.homeTab

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import wskim.aos.simpledutch.common.base.BaseViewEvent
import wskim.aos.simpledutch.common.base.BaseViewSideEffect
import wskim.aos.simpledutch.common.base.BaseViewState
import wskim.aos.simpledutch.common.base.SdV1ScreenStateEnum
import wskim.aos.simpledutch.progaurdSafeZone.HomeDutchListItemVO


class HomeContract {
    sealed class Event : BaseViewEvent {
        object OnResume : Event()
        object HomeWriteButtonClicked : Event()
    }

    data class State(
        override val screenState: MutableState<SdV1ScreenStateEnum>,
        val list: SnapshotStateList<HomeDutchListItemVO>
    ) : BaseViewState

    sealed class Effect : BaseViewSideEffect {
        sealed class Navigation : Effect() {
            object GoToHomeWrite : Navigation()
        }
    }
}