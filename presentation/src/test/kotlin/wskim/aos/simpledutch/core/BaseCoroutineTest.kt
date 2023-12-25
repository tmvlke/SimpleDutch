package wskim.aos.simpledutch.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.RegisterExtension
import wskim.aos.domain.utils.DateTimeUtils
import java.util.Date

abstract class BaseCoroutineTest {

    companion object {
        @OptIn(ExperimentalCoroutinesApi::class)
        @JvmField
        @RegisterExtension
        val coroutineExtension = MvvmDefaultTaskExecutorExtension()
    }

    abstract fun onStart()
    abstract fun onEnd()

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun processedOnStart() {
        println("onStart: ${DateTimeUtils.convertDate(Date())}")
        Dispatchers.setMain(coroutineExtension.dispatcher)
        onStart()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun processedOnEnd() {
        println("onEnd: ${DateTimeUtils.convertDate(Date())}")
        Dispatchers.resetMain()
        onEnd()
    }
}