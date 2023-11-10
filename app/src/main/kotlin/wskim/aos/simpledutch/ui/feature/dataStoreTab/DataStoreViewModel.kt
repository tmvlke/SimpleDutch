package wskim.aos.simpledutch.ui.feature.dataStoreTab

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import wskim.aos.simpledutch.common.base.SdV1ScreenStateEnum
import wskim.aos.simpledutch.common.base.SdV1ViewModel
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor() :
    SdV1ViewModel<DataStoreContract.Event, DataStoreContract.State, DataStoreContract.Effect>() {

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

    override fun setInitialState(): DataStoreContract.State = DataStoreContract.State(
        screenState = mutableStateOf(SdV1ScreenStateEnum.SUCCESS)
    )

    override suspend fun setInitialData() {
        // 데이터 추가
    }

    override fun handleUiMonitoring() {}

    override fun handleEvents(event: DataStoreContract.Event) {
//        when (event) {
//            is DataStoreContract.Event.SignUpButtonSelection -> setEffect {
//                DataStoreContract.Effect.Navigation.GoToSignUp
//            }
//        }
    }


}