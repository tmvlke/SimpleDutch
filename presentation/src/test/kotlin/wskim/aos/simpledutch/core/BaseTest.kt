package wskim.aos.simpledutch.core

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import wskim.aos.domain.utils.DateTimeUtils
import java.util.Date

abstract class BaseTest {

    abstract fun onStart()
    abstract fun onEnd()

    @BeforeEach
    fun processedOnStart() {
        println("onStart: ${DateTimeUtils.convertDate(Date())}")
        onStart()
    }

    @AfterEach
    fun processedOnEnd() {
        println("onEnd: ${DateTimeUtils.convertDate(Date())}")
        onEnd()
    }
}