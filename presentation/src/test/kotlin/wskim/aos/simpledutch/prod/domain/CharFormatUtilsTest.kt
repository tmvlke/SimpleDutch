package wskim.aos.simpledutch.prod.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import wskim.aos.domain.utils.CharFormatUtils
import wskim.aos.simpledutch.core.BaseCoroutineTest

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@DisplayName("문자열 형식 유틸 클래스 테스트")
class CharFormatUtilsTest : BaseCoroutineTest() {

    override fun onStart() {}

    override fun onEnd() {}

    @DisplayName("amount에 null 값을 전달하면 0원으로 반환되는가?")
    @Order(1)
    @Test
    fun t01() {
        val result = CharFormatUtils.amount(null)
        assertEquals("0원", result)
    }

    @DisplayName("amount에 100 단위 이하 숫자 값을 전달하면 천 단위 구분 기호 없는 금액으로 반환되는가?")
    @Order(2)
    @Test
    fun t02() {
        val result = CharFormatUtils.amount(156)
        assertEquals("156원", result)
    }

    @DisplayName("amount에 1000 단위 이상 숫자 값을 전달하면 천 단위 구분 기호를 포함한 금액으로 반환되는가?")
    @Order(3)
    @Test
    fun t03() {
        val result = CharFormatUtils.amount(1234567)
        assertEquals("1,234,567원", result)
    }

    @DisplayName("amount에 100 단위 이하 문자 값을 전달하면 천 단위 구분 기호 없는 금액으로 반환되는가?")
    @Order(4)
    @Test
    fun t04() {
        val result = CharFormatUtils.amount("156")
        assertEquals("156원", result)
    }

    @DisplayName("amount에 1000 단위 이상 문자 값을 전달하면 천 단위 구분 기호를 포함한 금액으로 반환되는가?")
    @Order(5)
    @Test
    fun t05() {
        val result = CharFormatUtils.amount("1234567")
        assertEquals("1,234,567원", result)
    }

    @DisplayName("amount에 숫자 형식이 아닌 값을 전달하면 0원이 반환되는가?")
    @Order(6)
    @Test
    fun t06() {
        val result = CharFormatUtils.amount("문자열")
        assertEquals("0원", result)
    }

    @DisplayName("amount에 소수점 형식이 값을 전달하면 0원이 반환되는가?")
    @Order(7)
    @Test
    fun t07() {
        val result = CharFormatUtils.amount("123.456")
        assertEquals("0원", result)
    }

    @DisplayName("amount에 숫자 형식이 아닌 값을 포함한 숫자 값을 전달하면 0원이 반환되는가?")
    @Order(8)
    @Test
    fun t08() {
        val result = CharFormatUtils.amount("123a456")
        assertEquals("0원", result)
    }
}