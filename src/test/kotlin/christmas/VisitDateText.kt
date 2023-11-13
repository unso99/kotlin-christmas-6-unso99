package christmas

import christmas.domain.validVisitDate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class VisitDateText {

    @ParameterizedTest
    @ValueSource(strings = ["", " ", "         "])
    fun `방문날짜가 공백이면 예외가 발생한다`(value: String) {
        assertThrows<IllegalArgumentException> {
            validVisitDate(value)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["wa", " !@^", "12-123123"])
    fun `방문날짜가 정수가 아니면 예외가 발생한다`(value: String) {
        assertThrows<IllegalArgumentException> {
            validVisitDate(value)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["0", "50", "-20"])
    fun `방문날짜가 1~31사이의 정수가 아니면 예외가 발생한다`(value: String) {
        assertThrows<IllegalArgumentException> {
            validVisitDate(value)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["1", "10", "31"])
    fun `방문 날짜가 올바르게 작성이 되었을 때는 입력이 정수로 나오게 된다`(value: String) {
        val result = validVisitDate(value)
        val expected = value.toInt()

        assertEquals(expected, result)
    }


}