package wskim.aos.hometab.feature.homeTab

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import wskim.aos.baseuikit.base.BaseViewEvent
import wskim.aos.baseuikit.base.BaseViewSideEffect
import wskim.aos.baseuikit.base.BaseViewState
import wskim.aos.baseuikit.base.SdV1ScreenStateEnum
import wskim.aos.domain.proguardSafeZone.vo.DutchListItemVO


class HomeContract {
    sealed class Event : BaseViewEvent {
        object OnResume : Event()
        object HomeWriteButtonClicked : Event()
        object HomeEndButtonClicked : Event()
    }

    data class State(
        override val screenState: MutableState<SdV1ScreenStateEnum>,
        val totalAmount: MutableState<Int>,
        val list: SnapshotStateList<DutchListItemVO>
    ) : BaseViewState

    sealed class Effect : BaseViewSideEffect {
        sealed class Toast : Effect() {
            object ShowListEmpty : Toast()
        }
        sealed class Navigation : Effect() {
            object GoToHomeWrite : Navigation()
            object GoToHomeEnd : Navigation()
        }
    }
}