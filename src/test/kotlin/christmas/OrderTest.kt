package christmas

import christmas.domain.getMenu
import christmas.domain.validVisitDate
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OrderTest {

    @ParameterizedTest
    @ValueSource(strings = [""," ","   "])
    fun `주문을 공백으로 하면 예외가 발생한다`(value : String){
        assertThrows<IllegalArgumentException> {
            getMenu(value)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["바비큐립 하나 주세요","티본스테이크-1개","티본스테이크-1, 바비큐립-2개","티본스테이크-1.바비큐립-2개"])
    fun `주문을 형식에 맞게 안하면 예외가 발생한다`(value : String){
        assertThrows<IllegalArgumentException> {
            getMenu(value)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["라면-1","콜라-1","삼겹살-2"])
    fun `메뉴에 없는 주문을 할 경우 예외가 발생한다`(value : String){
        assertThrows<IllegalArgumentException> {
            getMenu(value)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["티본스테이크-1개","티본스테이크-one","티본스테이크-한개"])
    fun `주문 개수에 숫자가 아닌 값을 작성하면 예외가 발생한다`(value : String){
        assertThrows<IllegalArgumentException> {
            getMenu(value)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["티본스테이크-0","바비큐립-0","티본스테이크-0,바비큐립-1"])
    fun `주문 개수에 0을 작성하면 예외가 발생한다`(value : String){
        assertThrows<IllegalArgumentException> {
            getMenu(value)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["티본스테이크-1,티본스테이크-1","티본스테이크-1,바비큐립-1,티본스테이크-1"])
    fun `중복된 주문을 작성하면 예외가 발생한다`(value : String){
        assertThrows<IllegalArgumentException> {
            getMenu(value)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["제로콜라-1","샴페인-1","레드와인-1"])
    fun `음료만 주문을 하면 예외가 발생한다`(value : String){
        assertThrows<IllegalArgumentException> {
            getMenu(value)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["티본스테이크-21","티본스테이크-1,바비큐립-19,초코케이크-2"])
    fun `총 음식개수가 20개를 넘어가면 예외가 발생한다`(value : String){
        assertThrows<IllegalArgumentException> {
            getMenu(value)
        }
    }


}