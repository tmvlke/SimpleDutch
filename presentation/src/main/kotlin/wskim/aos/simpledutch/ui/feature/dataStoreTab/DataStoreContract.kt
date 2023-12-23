package wskim.aos.simpledutch.ui.feature.dataStoreTab

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import wskim.aos.simpledutch.common.base.BaseViewEvent
import wskim.aos.simpledutch.common.base.BaseViewSideEffect
import wskim.aos.simpledutch.common.base.BaseViewState
import wskim.aos.simpledutch.common.base.SdV1ScreenStateEnum
import wskim.aos.domain.proguardSafeZone.vo.DutchHistoryListItemVO


class DataStoreContract {

    sealed class Event : BaseViewEvent {
        object OnResume : Event()
    }

    data class State(
        override val screenState: MutableState<SdV1ScreenStateEnum>,
        val list: SnapshotStateList<DutchHistoryListItemVO>
    ) : BaseViewState

    sealed class Effect : BaseViewSideEffect {

        sealed class Navigation : Effect() {
            object GoToSignIn : Navigation()
        }
    }
}
