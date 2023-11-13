package christmas

import christmas.domain.Badge
import christmas.domain.getBadge
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BadgeTest {

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 1_000, 4_999])
    fun `할인 혜택 금액이 5_000원 미만이면 배지 없음`(value: Int) {
        val result = getBadge(value)
        val expected = Badge.NONE

        assertEquals(expected, result)
    }

    @ParameterizedTest
    @ValueSource(ints = [5_000, 7_500, 9_999])
    fun `할인 혜택 금액이 5_000원 이상 10_000원 미만이면 별 배지 얻음`(value: Int) {
        val result = getBadge(value)
        val expected = Badge.STAR

        assertEquals(expected, result)
    }

    @ParameterizedTest
    @ValueSource(ints = [10_000, 15_000, 19_999])
    fun `할인 혜택 금액이 10_000원 이상 20_000원 미만이면 나무 배지 얻음`(value: Int) {
        val result = getBadge(value)
        val expected = Badge.TREE

        assertEquals(expected, result)
    }

    @ParameterizedTest
    @ValueSource(ints = [20_000, 25_000, 99999_999])
    fun `할인 혜택 금액이 20_000원 이상이면 산타 배지 얻음`(value: Int) {
        val result = getBadge(value)
        val expected = Badge.SANTA

        assertEquals(expected, result)
    }

}