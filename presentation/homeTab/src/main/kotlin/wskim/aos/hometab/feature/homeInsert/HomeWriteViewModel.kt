package wskim.aos.hometab.feature.homeInsert

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import wskim.aos.baseuikit.base.SdV1ScreenStateEnum
import wskim.aos.baseuikit.base.SdV1ViewModel
import wskim.aos.domain.usecase.DutchInfoUseCase
import wskim.aos.domain.proguardSafeZone.vo.DutchListItemVO
import wskim.aos.domain.proguardSafeZone.vo.DutchPersonVO
import javax.inject.Inject

@HiltViewModel
class HomeWriteViewModel @Inject constructor(
    private val dutchInfoUseCase: DutchInfoUseCase,
) : SdV1ViewModel<HomeWriteContract.Event, HomeWriteContract.State, HomeWriteContract.Effect>() {

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

    override fun setInitialState(): HomeWriteContract.State = HomeWriteContract.State(
        screenState = mutableStateOf(SdV1ScreenStateEnum.COMPLETE),
        title = MutableStateFlow(""),
        amount = MutableStateFlow(""),
        enterPersonName = mutableStateOf(""),
        enterPersonList = mutableStateListOf(),
        completeButtonEnabled = mutableStateOf(false)
    )

    override suspend fun setInitialData() {
        // 데이터 추가
    }

    override fun handleUiMonitoring() {
        viewState.value.title.onEach { processedDutchWriteValidation() }.launchIn(viewModelScope)
        viewState.value.amount.onEach { processedDutchWriteValidation() }.launchIn(viewModelScope)
    }

    override fun handleEvents(event: HomeWriteContract.Event) {
        when (event) {
            is HomeWriteContract.Event.BackButtonClicked -> backButtonClicked()
            is HomeWriteContract.Event.SaveEnterPersonClicked -> saveEnterPersonClicked()
            is HomeWriteContract.Event.RemoveEnterPersonClicked -> removeEnterPersonClicked(event.position)
            is HomeWriteContract.Event.CompleteButtonClicked -> completeButtonClicked()
        }
    }

    // 뒤로가기 버튼 클릭
    private fun backButtonClicked() {
        setEffect { HomeWriteContract.Effect.Navigation.GoToBack }
    }

    // 더치 페이 글쓰기 전 유효성 체크
    private fun processedDutchWriteValidation() {
        setState {
            copy(
                completeButtonEnabled = mutableStateOf(
                    dutchInfoUseCase.processedDutchWriteValidation(
                        title = title.value,
                        amount = amount.value,
                        enterPersonList = enterPersonList
                    )
                )
            )
        }
    }

    // 참여자 추가 버튼 클릭
    private fun saveEnterPersonClicked() {
        viewState.value.enterPersonList.add(viewState.value.enterPersonName.value)
        setState { copy(enterPersonName = mutableStateOf("")) }
        processedDutchWriteValidation()
    }

    // 참여자 삭제 버튼 클릭
    private fun removeEnterPersonClicked(position: Int) {
        viewState.value.enterPersonList.removeAt(position)
        processedDutchWriteValidation()
    }

    // 더치 페이 추가 버튼 클릭
    private fun completeButtonClicked() {
        // 저장 전 참여자 목록이 빈값이면 진행 불가
        if (!viewState.value.completeButtonEnabled.value) return


        // 저장 하기
        viewState.value.also {
            val enterPersonList = ArrayList<DutchPersonVO>()

            it.enterPersonList.onEach { name ->
                enterPersonList.add(
                    DutchPersonVO(
                        name = name,
                        isEnd = false
                    )
                )
            }

            dutchInfoUseCase.saveDutchInfo(
                DutchListItemVO(
                    title = it.title.value,
                    amount = it.amount.value,
                    enterPersonList = enterPersonList
                )
            )
        }

        // 저장 완료 토스트 노출 및 뒤로가기
        setEffect { HomeWriteContract.Effect.Toast.ShowComplete }
        setEffect { HomeWriteContract.Effect.Navigation.GoToBack }
    }
}