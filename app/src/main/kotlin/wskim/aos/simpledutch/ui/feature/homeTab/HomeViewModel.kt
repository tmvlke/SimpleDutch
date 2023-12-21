package wskim.aos.simpledutch.ui.feature.homeTab

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import dagger.hilt.android.lifecycle.HiltViewModel
import wskim.aos.simpledutch.common.base.SdV1ScreenStateEnum
import wskim.aos.simpledutch.common.base.SdV1ViewModel
import wskim.aos.simpledutch.core.bl.useCase.DutchInfoUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dutchInfoUseCase: DutchInfoUseCase,
) : SdV1ViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {

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

    override fun setInitialState(): HomeContract.State = HomeContract.State(
        screenState = mutableStateOf(SdV1ScreenStateEnum.SUCCESS),
        totalAmount = mutableIntStateOf(0),
        list = mutableStateListOf()
    )

    override suspend fun setInitialData() {
        // 데이터 추가
    }

    override fun handleUiMonitoring() {}

    override fun handleEvents(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.OnResume -> onResume()
            is HomeContract.Event.HomeWriteButtonClicked -> homeWriteButtonClicked()
            is HomeContract.Event.HomeEndButtonClicked -> homeEndButtonClicked()
        }
    }

    private fun onResume() {
        setState {
            copy(
                totalAmount = mutableIntStateOf(dutchInfoUseCase.findDutchTotalAmount()),
                list = dutchInfoUseCase.findDutchInfoList().toMutableStateList()
            )
        }
    }

    private fun homeWriteButtonClicked() {
        setEffect { HomeContract.Effect.Navigation.GoToHomeWrite }
    }

    private fun homeEndButtonClicked() {
        if (viewState.value.list.isEmpty()) {
            setEffect { HomeContract.Effect.Toast.ShowListEmpty }
            return
        }

        setEffect { HomeContract.Effect.Navigation.GoToHomeEnd }
    }
}