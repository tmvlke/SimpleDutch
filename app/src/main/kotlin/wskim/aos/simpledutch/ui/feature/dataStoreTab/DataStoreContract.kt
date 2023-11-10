package wskim.aos.simpledutch.ui.feature.dataStoreTab

import androidx.compose.runtime.MutableState
import wskim.aos.simpledutch.common.base.BaseViewEvent
import wskim.aos.simpledutch.common.base.BaseViewSideEffect
import wskim.aos.simpledutch.common.base.BaseViewState
import wskim.aos.simpledutch.common.base.SdV1ScreenStateEnum


class DataStoreContract {

    // 이벤트는 사용자의 상호작용이나 시스템 이벤트와 같은 외부 요소를 나타냅니다.
    // 사용자가 버튼을 클릭하거나 네트워크 요청의 응답을 받는 등의 상황이 이벤트로 간주될 수 있습니다.
    // 이벤트는 View에서 생성되며 ViewModel로 전달됩니다.
    // ViewModel은 이벤트를 기반으로 비즈니스 로직을 수행하고 상태 및 이펙트를 업데이트합니다.

    // 유저가 이렇게 하고 싶다는데?
    sealed class Event : BaseViewEvent {
        //        data class SignButtonSelection(val type: MainTabEnum) : Event()
    }

    // 상태는 애플리케이션의 현재 상태를 나타냅니다.
    // 예를 들어 화면에 표시되는 데이터, UI의 상태, 활성화된 버튼 등이 상태에 해당합니다.
    // ViewModel은 이벤트를 처리하고 상태를 업데이트하여 View에 반영합니다.
    // 이를 통해 화면이 적절하게 표시되고 사용자와 상호작용할 수 있습니다.

    // 뷰가 이 상태면 어떻게 할래?
    data class State(
        override val screenState: MutableState<SdV1ScreenStateEnum>
//        val initOpenTab: MainTabEnum
    ) : BaseViewState
    // 이펙트는 애플리케이션의 외부 영향을 나타냅니다.
    // 주로 비동기 작업의 결과나 네트워크 요청의 성공 또는 실패와 같은 외부 동작을 표현합니다.
    // ViewModel은 비동기 작업을 처리하고, 이펙트를 생성하여 View에 전달합니다.
    // View는 이펙트를 받아 UI를 업데이트하거나 필요한 조치를 취할 수 있습니다.
    // 예를 들어, 네트워크 요청이 성공하면 토스트 메시지를 표시하거나 다음 화면으로 이동하는 등의 동작을 수행할 수 있습니다.

    // 결과이 나왓어! 그니까 이제 뭐할래?
    sealed class Effect : BaseViewSideEffect {

//        object DataWasLoaded : Effect()
//        data class TabSelected(val type: MainTabEnum) : Effect()

        sealed class Navigation : Effect() {
            object GoToSignIn : Navigation()
        }
    }
}
