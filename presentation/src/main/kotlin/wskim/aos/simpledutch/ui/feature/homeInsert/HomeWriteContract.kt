package wskim.aos.simpledutch.ui.feature.homeInsert

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.flow.MutableStateFlow
import wskim.aos.simpledutch.common.base.BaseViewEvent
import wskim.aos.simpledutch.common.base.BaseViewSideEffect
import wskim.aos.simpledutch.common.base.BaseViewState
import wskim.aos.simpledutch.common.base.SdV1ScreenStateEnum


class HomeWriteContract {
    sealed class Event : BaseViewEvent {
        object BackButtonClicked : Event()
        object SaveEnterPersonClicked : Event()
        data class RemoveEnterPersonClicked(val position: Int) : Event()
        object CompleteButtonClicked : Event()
    }

    data class State(
        override val screenState: MutableState<SdV1ScreenStateEnum>,
        val title: MutableStateFlow<String>,
        val amount: MutableStateFlow<String>,
        val enterPersonName: MutableState<String>,
        val enterPersonList: SnapshotStateList<String>,
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
