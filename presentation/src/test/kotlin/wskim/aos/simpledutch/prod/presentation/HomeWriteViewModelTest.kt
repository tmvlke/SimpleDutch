package wskim.aos.simpledutch.prod.presentation

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import wskim.aos.domain.proguardSafeZone.vo.DutchListItemVO
import wskim.aos.domain.proguardSafeZone.vo.DutchPersonVO
import wskim.aos.domain.repository.DutchInfoRepository
import wskim.aos.domain.usecase.DutchInfoUseCase
import wskim.aos.simpledutch.core.BaseCoroutineTest
import wskim.aos.hometab.feature.homeInsert.HomeWriteContract
import wskim.aos.hometab.feature.homeInsert.HomeWriteViewModel

@ExperimentalCoroutinesApi
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@DisplayName("더치페이 등록 뷰모델 - 정보 저장 로직 테스트")
class HomeWriteViewModelTest : BaseCoroutineTest() {

    private lateinit var vm: HomeWriteViewModel
    private lateinit var dutchInfoUseCase: DutchInfoUseCase

    private lateinit var dutchInfoRepository: DutchInfoRepository

    private val title = "1차 회식"
    private val amount = "100000"
    private val enterPersonName = "참여자1"

    override fun onStart() {
        dutchInfoRepository = mockk()
        dutchInfoUseCase = DutchInfoUseCase(dutchInfoRepository = dutchInfoRepository)
        vm = HomeWriteViewModel(dutchInfoUseCase = dutchInfoUseCase)
    }

    override fun onEnd() {}

    @Test
    @Order(10)
    @DisplayName(
        "더치 페이 정보를 입력 할때 경우 -> " +
                "지출 내용만 입력 경우 -> " +
                "추가하기 버튼이 활성화 되지 않는 것이 맞는가?"
    )
    fun t10() = runBlocking {
        /***************************************************
         * when - 동작 실행
         ***************************************************/
        vm.viewState.value.title.value = title
        coroutineExtension.dispatcher.scheduler.advanceUntilIdle()
        /***************************************************
         * then - 결과 검증
         ***************************************************/
        assertEquals(false, vm.viewState.value.completeButtonEnabled.value)
    }

    @Test
    @Order(11)
    @DisplayName(
        "더치 페이 정보를 입력 할때 경우 -> " +
                "총 가격만 입력 경우 -> " +
                "추가하기 버튼이 활성화 되지 않는 것이 맞는가?"
    )
    fun t11() = runBlocking {
        /***************************************************
         * when - 동작 실행
         ***************************************************/
        vm.viewState.value.amount.value = amount
        coroutineExtension.dispatcher.scheduler.advanceUntilIdle()
        /***************************************************
         * then - 결과 검증
         ***************************************************/
        assertEquals(false, vm.viewState.value.completeButtonEnabled.value)
    }

    @Test
    @Order(12)
    @DisplayName(
        "더치 페이 정보를 입력 할때 경우 -> " +
                "참여자 목록만 입력 경우 -> " +
                "추가하기 버튼이 활성화 되지 않는 것이 맞는가?"
    )
    fun t12() = runBlocking {
        /***************************************************
         * when - 동작 실행
         ***************************************************/
        vm.viewState.value.enterPersonName.value = enterPersonName
        coroutineExtension.dispatcher.scheduler.advanceUntilIdle()

        vm.setEvent(HomeWriteContract.Event.SaveEnterPersonClicked)
        coroutineExtension.dispatcher.scheduler.advanceUntilIdle()
        /***************************************************
         * then - 결과 검증
         ***************************************************/
        assertEquals(false, vm.viewState.value.completeButtonEnabled.value)
    }

    @Test
    @Order(13)
    @DisplayName(
        "더치 페이 정보를 입력 할때 경우 -> " +
                "지출 내용 및 총 가격만 입력 경우 -> " +
                "추가하기 버튼이 활성화 되지 않는 것이 맞는가?"
    )
    fun t13() = runBlocking {
        /***************************************************
         * when - 동작 실행
         ***************************************************/
        vm.viewState.value.title.value = title
        coroutineExtension.dispatcher.scheduler.advanceUntilIdle()

        vm.viewState.value.amount.value = amount
        coroutineExtension.dispatcher.scheduler.advanceUntilIdle()
        /***************************************************
         * then - 결과 검증
         ***************************************************/
        assertEquals(false, vm.viewState.value.completeButtonEnabled.value)
    }

    @Test
    @Order(14)
    @DisplayName(
        "더치 페이 정보를 입력 할때 경우 -> " +
                "총 가격 및 참여자 목록만 입력 경우 -> " +
                "추가하기 버튼이 활성화 되지 않는 것이 맞는가?"
    )
    fun t14() = runBlocking {
        /***************************************************
         * when - 동작 실행
         ***************************************************/
        vm.viewState.value.amount.value = amount
        coroutineExtension.dispatcher.scheduler.advanceUntilIdle()

        vm.viewState.value.enterPersonName.value = enterPersonName
        coroutineExtension.dispatcher.scheduler.advanceUntilIdle()

        vm.setEvent(HomeWriteContract.Event.SaveEnterPersonClicked)
        coroutineExtension.dispatcher.scheduler.advanceUntilIdle()
        /***************************************************
         * then - 결과 검증
         ***************************************************/
        assertEquals(false, vm.viewState.value.completeButtonEnabled.value)
    }

    @Test
    @Order(15)
    @DisplayName(
        "더치 페이 정보를 입력 할때 경우 -> " +
                "지출 내용 및 참여자 목록만 입력 경우 -> " +
                "추가하기 버튼이 활성화 되지 않는 것이 맞는가?"
    )
    fun t15() = runBlocking {
        /***************************************************
         * when - 동작 실행
         ***************************************************/
        vm.viewState.value.title.value = title
        coroutineExtension.dispatcher.scheduler.advanceUntilIdle()

        vm.viewState.value.enterPersonName.value = enterPersonName
        coroutineExtension.dispatcher.scheduler.advanceUntilIdle()

        vm.setEvent(HomeWriteContract.Event.SaveEnterPersonClicked)
        coroutineExtension.dispatcher.scheduler.advanceUntilIdle()
        /***************************************************
         * then - 결과 검증
         ***************************************************/
        assertEquals(false, vm.viewState.value.completeButtonEnabled.value)
    }



    @Test
    @Order(20)
    @DisplayName(
        "더치 페이 정보 입력 완료 후 -> " +
                "추가하기 버튼을 눌렀을 때 -> " +
                "데이터 저장 후 토스트 노출 및 이전 페이지로 이동 되는 것이 맞는가?"
    )
    fun t20() = runBlocking {
        /***************************************************
         * given - 가정
         ***************************************************/
        coEvery {
            dutchInfoRepository.insertDutchInfo(
                DutchListItemVO(
                    title = title,
                    amount = amount,
                    enterPersonList = arrayListOf(
                        DutchPersonVO(
                            name = enterPersonName,
                            isEnd = false
                        )
                    )
                )
            )
        } returns (Unit)
        /***************************************************
         * when - 동작 실행
         ***************************************************/
        vm.viewState.value.title.value = title
        coroutineExtension.dispatcher.scheduler.advanceUntilIdle()

        vm.viewState.value.amount.value = amount
        coroutineExtension.dispatcher.scheduler.advanceUntilIdle()

        vm.viewState.value.enterPersonName.value = enterPersonName
        coroutineExtension.dispatcher.scheduler.advanceUntilIdle()

        vm.setEvent(HomeWriteContract.Event.SaveEnterPersonClicked)
        coroutineExtension.dispatcher.scheduler.advanceUntilIdle()

        vm.setEvent(HomeWriteContract.Event.CompleteButtonClicked)
        coroutineExtension.dispatcher.scheduler.advanceUntilIdle()

        // effect 의 마지막 값을 가져옵니다.
        var toast : HomeWriteContract.Effect? = null
        var navigation : HomeWriteContract.Effect? = null
        // 가져오고자 하는 flow
        vm.effect.take(2).collectIndexed { index, value ->
            if (index == 0) {
                toast = value
            } else {
                navigation = value
            }
        }
        // advanceUntilIdle 을 사용하면 대기중인 코루틴을 계속 기다리기 때문에 1초만 기다리기
        coroutineExtension.dispatcher.scheduler.advanceTimeBy(1000)


        /***************************************************
         * then - 결과 검증
         ***************************************************/
        assertEquals(true, vm.viewState.value.completeButtonEnabled.value)
        assertEquals(HomeWriteContract.Effect.Toast.ShowComplete, toast)
        assertEquals(HomeWriteContract.Effect.Navigation.GoToBack, navigation)
    }
}