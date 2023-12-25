package wskim.aos.hometab.feature.homeEnd

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import wskim.aos.baseuikit.base.BaseViewEvent
import wskim.aos.baseuikit.base.BaseViewSideEffect
import wskim.aos.baseuikit.base.BaseViewState
import wskim.aos.baseuikit.base.SdV1ScreenStateEnum
import wskim.aos.domain.proguardSafeZone.vo.DutchEndListItemVO


class HomeEndContract {
    sealed class Event : BaseViewEvent {
        object BackButtonClicked : Event()
        data class EndButtonClicked(val position: Int) : Event()
        object CompleteButtonClicked : Event()
    }

    data class State(
        override val screenState: MutableState<SdV1ScreenStateEnum>,
        val totalAmount: MutableState<Int>,
        val enterPersonList: SnapshotStateList<DutchEndListItemVO>,
        val completeButtonEnabled: MutableState<Boolean>
    ) : BaseViewState

    sealed class Effect : BaseViewSideEffect {
        sealed class Toast : Effect() {
            object ShowComplete : Toast()
        }

        sealed class Navigation : Effect() {
            object GoToBack : Navigation()
        }
    }
}
