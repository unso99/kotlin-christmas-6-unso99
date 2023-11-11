package christmas.domain

import java.time.DayOfWeek
import java.time.LocalDate

enum class DiscountType(val event: String) {
    CHRISTMAS("크리스마스 디데이 할인: "), WEEKDAY("평일 할인: "), WEEKEND("주말 할인: "), SPECIAL("특별 할인: "), GIFT("증정 이벤트: ")
}

data class Discount(val discountPrice: Int, val type: DiscountType)

class EventCalendar(private val date: Int, private val hasGift : Boolean) {

    private val christmasDiscount = Discount(1_000, DiscountType.CHRISTMAS)
    private val weekdayDiscount = Discount(2_023, DiscountType.WEEKDAY)
    private val weekendDiscount = Discount(2_023, DiscountType.WEEKEND)
    private val specialDiscount = Discount(1_000, DiscountType.SPECIAL)
    private val specialDiscountDays = setOf(3, 10, 17, 24, 25, 31)
    private val giftDiscount = Discount(25_000,DiscountType.GIFT)

    private val currentDate = LocalDate.of(2023, 12, date)


    fun getDiscountInfo(): List<Discount> {
        val discountList = mutableListOf<Discount>()

        if (date <= 25) {
            val plusDiscount = (date - 1) * 100
            discountList.add(christmasDiscount.copy(discountPrice = 1000 + plusDiscount))
        }

        if (currentDate.dayOfWeek <= DayOfWeek.THURSDAY || currentDate.dayOfWeek == DayOfWeek.SUNDAY) {
            discountList.add(weekdayDiscount)
        }

        if (currentDate.dayOfWeek == DayOfWeek.FRIDAY || currentDate.dayOfWeek == DayOfWeek.SATURDAY) {
            discountList.add(weekendDiscount)
        }

        if (specialDiscountDays.contains(date)) {
            discountList.add(specialDiscount)
        }

        if(hasGift){
            discountList.add(giftDiscount)
        }

        return discountList
    }
}