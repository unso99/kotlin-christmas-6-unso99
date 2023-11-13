package christmas.domain

import christmas.Constant.ZERO
import christmas.domain.DiscountType.*
import christmas.view.OutputView

class Pos(private val orderList: Map<String, Int>) {
    private val orderMenuTypes = mutableMapOf(
        MenuType.APPETIZER to ZERO,
        MenuType.MAIN to ZERO,
        MenuType.DESSERT to ZERO,
        MenuType.BEVERAGE to ZERO
    )

    private val discountInfo = mutableMapOf<String, Int>()

    private val outputView = OutputView()

    init {
        countMenuType()
        showTotalPrice(calculateTotalPrice(orderList))
    }

    fun getTotalPrice() = calculateTotalPrice(orderList)

    fun getEventDiscount() = calculateTotalEventDiscount()

    fun getExpectedPrice(hasGift: Boolean): Int {
        val totalPrice = calculateTotalPrice(orderList)
        val discountPrice = calculateTotalEventDiscount()
        var expectedPrice = (totalPrice - discountPrice)
        if (hasGift) {
            expectedPrice += GIFT.discountPrice
        }
        return expectedPrice
    }

    fun showEventDiscount(discount: List<Discount>) {
        val eventDiscount = calculateEventDiscount(discount)

        eventDiscount.forEach {
            outputView.printEventDiscount(it.key, it.value)
        }
        if (eventDiscount.isEmpty()) outputView.printNone()
        println()
    }

    fun showTotalEventDiscount() {
        val totalDiscount = calculateTotalEventDiscount()
        outputView.printTotalEventDiscount(totalDiscount)
    }

    private fun countMenuType() {
        for (order in orderList) {
            val menu = Menu.entries.find { it.menuName == order.key }
            menu?.let {
                orderMenuTypes.compute(it.type) { _, count -> (count ?: ZERO) + order.value }
            }
        }
    }

    private fun calculateTotalPrice(orderList: Map<String, Int>): Int {
        var totalPrice = ZERO

        for (order in orderList) {
            val menu = Menu.entries.find { it.menuName == order.key }
            menu?.let {
                totalPrice += it.price * order.value
            }
        }

        return totalPrice
    }

    private fun showTotalPrice(price: Int) {
        outputView.printPrice(price)
    }

    private fun calculateEventDiscount(discount: List<Discount>): Map<String, Int> {
        discount.forEach {
            when (it.type) {
                CHRISTMAS -> { updateDiscount(it) }
                WEEKDAY -> { updateWeekDayDiscount(it) }
                WEEKEND -> { updateWeekendDiscount(it) }
                SPECIAL -> { updateDiscount(it) }
                GIFT -> { updateDiscount(it) }
            }
        }

        return discountInfo.filter { it.value != ZERO }
    }


    private fun updateDiscount(it: Discount) {
        discountInfo.compute(it.type.event) { _, price -> (price ?: ZERO) + it.discountPrice }
    }

    private fun updateWeekDayDiscount(it: Discount) {
        val dessertCount = orderMenuTypes[MenuType.DESSERT]!!
        discountInfo.compute(it.type.event) { _, price ->
            (price ?: ZERO) + it.discountPrice * dessertCount
        }
    }

    private fun updateWeekendDiscount(it: Discount) {
        val mainCount = orderMenuTypes[MenuType.MAIN]!!
        discountInfo.compute(it.type.event) { _, price ->
            (price ?: ZERO) + it.discountPrice * mainCount
        }
    }


    private fun calculateTotalEventDiscount(): Int {
        var totalPrice = ZERO
        discountInfo.forEach {
            totalPrice += it.value
        }

        return totalPrice
    }


}