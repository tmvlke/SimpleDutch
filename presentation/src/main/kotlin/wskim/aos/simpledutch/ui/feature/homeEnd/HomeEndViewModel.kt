package wskim.aos.simpledutch.ui.feature.homeEnd

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import dagger.hilt.android.lifecycle.HiltViewModel
import wskim.aos.simpledutch.common.base.SdV1ScreenStateEnum
import wskim.aos.simpledutch.common.base.SdV1ViewModel
import wskim.aos.domain.usecase.DutchInfoUseCase
import javax.inject.Inject

@HiltViewModel
class HomeEndViewModel @Inject constructor(
    private val dutchInfoUseCase: DutchInfoUseCase,
) : SdV1ViewModel<HomeEndContract.Event, HomeEndContract.State, HomeEndContract.Effect>() {

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

    override fun setInitialState(): HomeEndContract.State = HomeEndContract.State(
        screenState = mutableStateOf(SdV1ScreenStateEnum.COMPLETE),
        totalAmount = mutableIntStateOf(0),
        enterPersonList = mutableStateListOf(),
        completeButtonEnabled = mutableStateOf(false)
    )

    override suspend fun setInitialData() {
        // 데이터 추가
        setState {
            copy(
                totalAmount = mutableIntStateOf(dutchInfoUseCase.findDutchTotalAmount()),
                enterPersonList = dutchInfoUseCase.findDutchEndInfoList().toMutableStateList()
            )
        }
        // 전체 정산 버튼 활성화 여부 체크
        processedDutchEndValidation()
    }

    override fun handleUiMonitoring() {}

    override fun handleEvents(event: HomeEndContract.Event) {
        when (event) {
            is HomeEndContract.Event.BackButtonClicked -> backButtonClicked()
            is HomeEndContract.Event.EndButtonClicked -> endButtonClicked(event.position)
            is HomeEndContract.Event.CompleteButtonClicked -> completeButtonClicked()
        }
    }

    // 뒤로가기 버튼 클릭
    private fun backButtonClicked() {
        setEffect { HomeEndContract.Effect.Navigation.GoToBack }
    }

    private fun processedDutchEndValidation() {

        setState {
            copy(
                completeButtonEnabled = mutableStateOf(
                    viewState.value.enterPersonList.map { it.isEnd }.none { !it }
                )
            )
        }
    }

    // 참여자 개별 정산 버튼 클릭
    private fun endButtonClicked(position: Int) {
        setState {
            copy(
                enterPersonList = enterPersonList.apply {
                    this[position] = this[position].copy(isEnd = !this[position].isEnd)

                    // 개별 정산 하기
                    dutchInfoUseCase.modifyDutchEndSomeInfo(
                        name = this[position].name
                    )
                }
            )
        }

        processedDutchEndValidation()
    }

    // 더치 페이 전체 정산 버튼 클릭
    private fun completeButtonClicked() {
        // 저장 전 참여자 목록이 빈값이면 진행 불가
        if (!viewState.value.completeButtonEnabled.value) return

        // 전체 정산 하기
        dutchInfoUseCase.modifyDutchEndAllInfo()

        // 저장 완료 토스트 노출 및 뒤로가기
        setEffect { HomeEndContract.Effect.Toast.ShowComplete }
        setEffect { HomeEndContract.Effect.Navigation.GoToBack }
    }
}