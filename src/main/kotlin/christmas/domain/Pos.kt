package christmas.domain

import christmas.Constant.ZERO
import christmas.domain.DiscountType.*

class Pos(private val orderList: Map<String, Int>) {
    private val orderMenuTypes = mutableMapOf(
        MenuType.APPETIZER to ZERO,
        MenuType.MAIN to ZERO,
        MenuType.DESSERT to ZERO,
        MenuType.BEVERAGE to ZERO
    )

    private val discountInfo = mutableMapOf<String, Int>()

    init {
        countMenuType()
        showTotalPrice(calculateTotalPrice(orderList))

    }

    fun getTotalPrice() = calculateTotalPrice(orderList)

    fun getEventDiscount() = calculateTotalEventDiscount()

    fun showEventDiscount(discount: List<Discount>) {
        val eventDiscount = calculateEventDiscount(discount)

        eventDiscount.forEach {
            println("${it.key}-${"%,d".format(it.value)}원")
        }
        println()
    }

    fun showTotalEventDiscount() {
        val totalDiscount = calculateTotalEventDiscount()
        println("-${"%,d".format(totalDiscount)}원")
        println()
    }

    fun showExpectedPrice() {
        val totalPrice = calculateTotalPrice(orderList)
        val discountPrice = calculateTotalEventDiscount()
        val expectedPrice = (totalPrice - discountPrice) + GIFT.discountPrice

        println("${"%,d".format(expectedPrice)}원")
        println()

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
        println("${"%,d".format(price)}원")
        println()
    }

    private fun calculateEventDiscount(discount: List<Discount>): Map<String, Int> {
        discount.forEach {
            when (it.type) {
                CHRISTMAS -> {
                    discountInfo.compute(it.type.event) { _, price -> (price ?: ZERO) + it.discountPrice }
                }

                WEEKDAY -> {
                    val dessertCount = orderMenuTypes[MenuType.DESSERT]!!
                    discountInfo.compute(it.type.event) { _, price ->
                        (price ?: ZERO) + it.discountPrice * dessertCount
                    }
                }

                WEEKEND -> {
                    val mainCount = orderMenuTypes[MenuType.MAIN]!!
                    discountInfo.compute(it.type.event) { _, price ->
                        (price ?: ZERO) + it.discountPrice * mainCount
                    }
                }

                SPECIAL -> {
                    discountInfo.compute(it.type.event) { _, price -> (price ?: ZERO) + it.discountPrice }
                }

                GIFT -> {
                    discountInfo.compute(it.type.event) { _, price -> (price ?: ZERO) + it.discountPrice }
                }
            }
        }

        return discountInfo

    }

    private fun calculateTotalEventDiscount(): Int {
        var totalPrice = ZERO
        discountInfo.forEach {
            totalPrice += it.value
        }

        return totalPrice
    }


}