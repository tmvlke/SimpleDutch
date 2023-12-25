package wskim.aos.storagetab.feature.dataStoreTab

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import dagger.hilt.android.lifecycle.HiltViewModel
import wskim.aos.baseuikit.base.SdV1ScreenStateEnum
import wskim.aos.baseuikit.base.SdV1ViewModel
import wskim.aos.domain.usecase.DutchInfoUseCase
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val dutchInfoUseCase: DutchInfoUseCase,
) : SdV1ViewModel<DataStoreContract.Event, DataStoreContract.State, DataStoreContract.Effect>() {

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
        screenState = mutableStateOf(SdV1ScreenStateEnum.COMPLETE),
        list = mutableStateListOf()
    )

    override suspend fun setInitialData() {
        // 데이터 추가
    }

    override fun handleUiMonitoring() {}

    override fun handleEvents(event: DataStoreContract.Event) {
        when (event) {
            is DataStoreContract.Event.OnResume -> onResume()
        }
    }

    private fun onResume() {
        setState {
            copy(
                list = dutchInfoUseCase.findDutchHistoryInfoList().toMutableStateList()
            )
        }
    }
}