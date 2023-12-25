package wskim.aos.simpledutch.devLab

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import wskim.aos.simpledutch.core.BaseTest

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@DisplayName("테스트")
class ExampleUnitTest : BaseTest() {

    override fun onStart() {}

    override fun onEnd() {}

    @DisplayName("")
    @Order(1)
    @Test
    fun t01() {
        assertEquals(4, 2 + 2)
    }
}