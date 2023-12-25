package wskim.aos.baseuikit.base

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

abstract class SdV1ViewModel<Event: BaseViewEvent, UiState: BaseViewState, Effect: BaseViewSideEffect> : BaseViewModel<Event, UiState, Effect>() {

    override fun handleEvents(event: Event) {}

    fun onCreate(
        initialData: suspend () -> Unit,
        initialHandleUiMonitoring: () -> Unit
    ) {
        viewModelScope.launch {
            // 데이터 추가
            val job1 = async(Dispatchers.Main) {
                initialData.invoke()
            }
            // ui 모니터링
            val job2 = async(Dispatchers.Main) {
                initialHandleUiMonitoring.invoke()
            }

            val deferredList = listOf(job1, job2)

            deferredList.awaitAll()
        }
    }

    private suspend fun initialData() = suspendCoroutine { continuation ->
        continuation.resume(AsyncDoneStatus.COMPLETE)
    }

    private suspend fun initialHandleUiMonitoring() = suspendCoroutine { continuation ->
        continuation.resume(AsyncDoneStatus.COMPLETE)
    }

    override fun handleUiMonitoring() {}

    enum class AsyncDoneStatus(val value: Int) {
        COMPLETE(1),
        FAIL(2)
    }
}