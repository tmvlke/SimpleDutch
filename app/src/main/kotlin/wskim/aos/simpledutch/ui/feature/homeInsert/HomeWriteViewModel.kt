package wskim.aos.simpledutch.ui.feature.homeInsert

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import wskim.aos.simpledutch.common.base.SdV1ScreenStateEnum
import wskim.aos.simpledutch.common.base.SdV1ViewModel
import wskim.aos.simpledutch.core.bl.useCase.UserInfoUseCase
import javax.inject.Inject

@HiltViewModel
class HomeWriteViewModel @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase,
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
        screenState = mutableStateOf(SdV1ScreenStateEnum.SUCCESS),
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
        viewState.value.title.onEach {
            setState {
                copy(
                    completeButtonEnabled = mutableStateOf(
                        it.isNotEmpty() && amount.value.isNotEmpty() && enterPersonList.isNotEmpty()
                    )
                )
            }
        }.launchIn(viewModelScope)

        viewState.value.amount.onEach {
            setState {
                copy(
                    completeButtonEnabled = mutableStateOf(
                        title.value.isNotEmpty() && it.isNotEmpty() && enterPersonList.isNotEmpty()
                    )
                )
            }
        }.launchIn(viewModelScope)
    }

    override fun handleEvents(event: HomeWriteContract.Event) {
        when (event) {
            is HomeWriteContract.Event.BackButtonClicked -> setEffect {
                HomeWriteContract.Effect.Navigation.GoToBack
            }

            is HomeWriteContract.Event.SaveEnterPersonClicked -> {

                if (viewState.value.enterPersonName.value.isEmpty()) return

                viewState.value.enterPersonList.add(viewState.value.enterPersonName.value)
                setState {
                    copy(
                        enterPersonName = mutableStateOf(""),
                        completeButtonEnabled = mutableStateOf(
                            title.value.isNotEmpty() && amount.value.isNotEmpty() && enterPersonList.isNotEmpty()
                        )
                    )
                }
            }

            is HomeWriteContract.Event.RemoveEnterPersonClicked -> {
                viewState.value.enterPersonList.removeAt(event.position)
                setState {
                    copy(
                        completeButtonEnabled = mutableStateOf(
                            title.value.isNotEmpty() && amount.value.isNotEmpty() && enterPersonList.isNotEmpty()
                        )
                    )
                }
            }

            is HomeWriteContract.Event.CompleteButtonClicked -> {
                if (!viewState.value.completeButtonEnabled.value) return

                setEffect { HomeWriteContract.Effect.Toast.ShowComplete }
                setEffect { HomeWriteContract.Effect.Navigation.GoToBack }
            }
        }
    }
}