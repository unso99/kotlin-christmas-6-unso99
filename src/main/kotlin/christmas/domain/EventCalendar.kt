package christmas.domain

import java.time.DayOfWeek
import java.time.LocalDate

enum class DiscountType(val event : String) {
    CHRISTMAS("크리스마스 디데이 할인: "), WEEKDAY("평일 할인: "), WEEKEND("주말 할인: "), SPECIAL("특별 할인: ")
}

data class Discount(val discountPrice: Int, val type: DiscountType)

class EventCalendar(private val date: Int) {

    private val christmasDiscount = Discount(1000, DiscountType.CHRISTMAS)
    private val weekdayDiscount = Discount(2023, DiscountType.WEEKDAY)
    private val weekendDiscount = Discount(2023, DiscountType.WEEKEND)
    private val specialDiscount = Discount(1000, DiscountType.SPECIAL)
    private val currentDate = LocalDate.of(2023, 12, date)

    private val specialDiscountDays = setOf(3, 10, 17, 24, 25, 31)

    fun getDiscountInfo(date: Int) : List<Discount> {
        val discountList  = mutableListOf<Discount>()
        when {
            currentDate.dayOfWeek in DayOfWeek.SUNDAY..DayOfWeek.THURSDAY -> {
                discountList.add(weekdayDiscount)
            }

            currentDate.dayOfWeek in DayOfWeek.FRIDAY..DayOfWeek.SATURDAY -> {
                discountList.add(weekendDiscount)
            }
        }

        if (specialDiscountDays.contains(date)) {
            discountList.add(specialDiscount)
        }

        if (date <= 25){
            val plusDiscount = (date -1) * 100
            discountList.add(christmasDiscount.copy(discountPrice = 1000+ plusDiscount))
        }

        return discountList
    }
}