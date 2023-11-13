package christmas

import christmas.domain.Discount
import christmas.domain.DiscountType
import christmas.domain.EventCalendar
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class EventCalendarTest {
    private lateinit var eventCalendar: EventCalendar

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 8, 9, 15, 16, 22, 23])
    fun `크리스마스할인과 주말 할인이 포함된 이벤트 정보를 줌`(value: Int) {
        eventCalendar = EventCalendar(value, false)
        val plusDiscount = (value - 1) * EventCalendar.CHRISTMAS_DISCOUNT_INCREMENT
        val result = eventCalendar.getDiscountInfo()
        val expected = listOf(
            Discount(DiscountType.CHRISTMAS.discountPrice + plusDiscount, DiscountType.CHRISTMAS),
            Discount(DiscountType.WEEKEND.discountPrice, DiscountType.WEEKEND)
        )
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 8, 9, 15, 16, 22, 23])
    fun `크리스마스할인,주말 할인, 증정할인이 포함된 이벤트 정보를 줌`(value: Int) {
        eventCalendar = EventCalendar(value, true)
        val plusDiscount = (value - 1) * EventCalendar.CHRISTMAS_DISCOUNT_INCREMENT
        val result = eventCalendar.getDiscountInfo()
        val expected = listOf(
            Discount(DiscountType.CHRISTMAS.discountPrice + plusDiscount, DiscountType.CHRISTMAS),
            Discount(DiscountType.WEEKEND.discountPrice, DiscountType.WEEKEND),
            Discount(DiscountType.GIFT.discountPrice, DiscountType.GIFT)
        )
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @ValueSource(ints = [4, 5, 6, 7, 11, 12, 13, 14, 18, 19, 20, 21])
    fun `크리스마스할인,평일할인이 포함된 이벤트 정보를 줌`(value: Int) {
        eventCalendar = EventCalendar(value, false)
        val plusDiscount = (value - 1) * EventCalendar.CHRISTMAS_DISCOUNT_INCREMENT
        val result = eventCalendar.getDiscountInfo()
        val expected = listOf(
            Discount(DiscountType.CHRISTMAS.discountPrice + plusDiscount, DiscountType.CHRISTMAS),
            Discount(DiscountType.WEEKDAY.discountPrice, DiscountType.WEEKDAY)
        )
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @ValueSource(ints = [4, 5, 6, 7, 11, 12, 13, 14, 18, 19, 20, 21])
    fun `크리스마스할인,평일할인, 증정할인이 포함된 이벤트 정보를 줌`(value: Int) {
        eventCalendar = EventCalendar(value, true)
        val plusDiscount = (value - 1) * EventCalendar.CHRISTMAS_DISCOUNT_INCREMENT
        val result = eventCalendar.getDiscountInfo()
        val expected = listOf(
            Discount(DiscountType.CHRISTMAS.discountPrice + plusDiscount, DiscountType.CHRISTMAS),
            Discount(DiscountType.WEEKDAY.discountPrice, DiscountType.WEEKDAY),
            Discount(DiscountType.GIFT.discountPrice, DiscountType.GIFT)
        )
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @ValueSource(ints = [3, 10, 17, 24, 25])
    fun `크리스마스할인, 평일할인,특별할인이 포함된 이벤트 정보를 줌`(value: Int) {
        eventCalendar = EventCalendar(value, false)
        val plusDiscount = (value - 1) * EventCalendar.CHRISTMAS_DISCOUNT_INCREMENT
        val result = eventCalendar.getDiscountInfo()
        val expected = listOf(
            Discount(DiscountType.CHRISTMAS.discountPrice + plusDiscount, DiscountType.CHRISTMAS),
            Discount(DiscountType.WEEKDAY.discountPrice, DiscountType.WEEKDAY),
            Discount(DiscountType.SPECIAL.discountPrice, DiscountType.SPECIAL)
        )
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @ValueSource(ints = [3, 10, 17, 24, 25])
    fun `크리스마스할인, 평일할인, 특별할인, 증정할인이 포함된 이벤트 정보를 줌`(value: Int) {
        eventCalendar = EventCalendar(value, true)
        val plusDiscount = (value - 1) * EventCalendar.CHRISTMAS_DISCOUNT_INCREMENT
        val result = eventCalendar.getDiscountInfo()
        val expected = listOf(
            Discount(DiscountType.CHRISTMAS.discountPrice + plusDiscount, DiscountType.CHRISTMAS),
            Discount(DiscountType.WEEKDAY.discountPrice, DiscountType.WEEKDAY),
            Discount(DiscountType.SPECIAL.discountPrice, DiscountType.SPECIAL),
            Discount(DiscountType.GIFT.discountPrice, DiscountType.GIFT)
        )
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @ValueSource(ints = [26, 27, 28])
    fun `평일할인이 포함된 이벤트 정보를 줌`(value: Int) {
        eventCalendar = EventCalendar(value, false)
        val result = eventCalendar.getDiscountInfo()
        val expected = listOf(Discount(DiscountType.WEEKDAY.discountPrice, DiscountType.WEEKDAY))
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @ValueSource(ints = [26, 27, 28])
    fun `평일할인, 증정할인이 포함된 이벤트 정보를 줌`(value: Int) {
        eventCalendar = EventCalendar(value, true)
        val result = eventCalendar.getDiscountInfo()
        val expected = listOf(
            Discount(DiscountType.WEEKDAY.discountPrice, DiscountType.WEEKDAY),
            Discount(DiscountType.GIFT.discountPrice, DiscountType.GIFT)
        )
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @ValueSource(ints = [31])
    fun `평일할인, 특별할인이 포함된 이벤트 정보를 줌`(value: Int) {
        eventCalendar = EventCalendar(value, false)
        val result = eventCalendar.getDiscountInfo()
        val expected = listOf(
            Discount(DiscountType.WEEKDAY.discountPrice, DiscountType.WEEKDAY),
            Discount(DiscountType.SPECIAL.discountPrice, DiscountType.SPECIAL)
        )
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @ValueSource(ints = [31])
    fun `평일할인, 특별할인, 증정할인이 포함된 이벤트 정보를 줌`(value: Int) {
        eventCalendar = EventCalendar(value, true)
        val result = eventCalendar.getDiscountInfo()
        val expected = listOf(
            Discount(DiscountType.WEEKDAY.discountPrice, DiscountType.WEEKDAY),
            Discount(DiscountType.SPECIAL.discountPrice, DiscountType.SPECIAL),
            Discount(DiscountType.GIFT.discountPrice, DiscountType.GIFT)
        )
        assertEquals(expected, result)
    }


}