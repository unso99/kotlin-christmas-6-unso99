package christmas

import christmas.domain.Discount
import christmas.domain.DiscountType
import christmas.domain.Menu
import christmas.domain.Pos
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PosTest {
    private lateinit var pos: Pos
    private val orderList =
        mapOf(
            Menu.T_BONE_STAKE.menuName to 1,
            Menu.BBQ_RIB.menuName to 1,
            Menu.CHOCOLATE_CAKE.menuName to 2,
            Menu.ZERO_COKE.menuName to 1
        )
    private val discountInfo = listOf(
        Discount(DiscountType.CHRISTMAS.discountPrice, DiscountType.CHRISTMAS),
        Discount(DiscountType.WEEKDAY.discountPrice, DiscountType.WEEKDAY),
        Discount(DiscountType.SPECIAL.discountPrice, DiscountType.SPECIAL),
        Discount(DiscountType.GIFT.discountPrice, DiscountType.GIFT)
    )

    @BeforeEach
    fun setUp() {
        pos = Pos(orderList)
        pos.showEventDiscount(discountInfo)
    }

    @Test
    fun `주문 리스트의 총 가격을 계산함`() {
        val result = pos.getTotalPrice()
        val expected = 142_000

        assertEquals(expected, result)
    }

    @Test
    fun `주문 리스트의 총 할인 금액을 계산함`() {
        val result = pos.getEventDiscount()
        val expected = 31_046

        assertEquals(expected, result)
    }

    @Test
    fun `주문 리스트의 총 예상 금액을 계산함`() {
        val hasGift = true
        val result = pos.getExpectedPrice(hasGift)

        val expected = 135_954

        assertEquals(expected, result)

    }

}