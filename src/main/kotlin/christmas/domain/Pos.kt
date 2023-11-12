package christmas.domain

import christmas.domain.DiscountType.*

class Pos(private val orderList: Map<String, Int>) {
    private val orderMenuTypes = mutableMapOf(
        MenuType.APPETIZER to INIT_VALUE,
        MenuType.MAIN to INIT_VALUE,
        MenuType.DESSERT to INIT_VALUE,
        MenuType.BEVERAGE to INIT_VALUE
    )

    private val discountInfo = mutableMapOf<String, Int>()

    init {
        orderOnlyBeverage(orderList)
        orderExceeded(orderList)
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
                orderMenuTypes.compute(it.type) { _, count -> (count ?: INIT_VALUE) + order.value }
            }
        }
    }

    private fun calculateTotalPrice(orderList: Map<String, Int>): Int {
        var totalPrice = INIT_VALUE

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
                    discountInfo.compute(it.type.event) { _, price -> (price ?: INIT_VALUE) + it.discountPrice }
                }

                WEEKDAY -> {
                    val dessertCount = orderMenuTypes[MenuType.DESSERT]!!
                    discountInfo.compute(it.type.event) { _, price ->
                        (price ?: INIT_VALUE) + it.discountPrice * dessertCount
                    }
                }

                WEEKEND -> {
                    val mainCount = orderMenuTypes[MenuType.MAIN]!!
                    discountInfo.compute(it.type.event) { _, price ->
                        (price ?: INIT_VALUE) + it.discountPrice * mainCount
                    }
                }

                SPECIAL -> {
                    discountInfo.compute(it.type.event) { _, price -> (price ?: INIT_VALUE) + it.discountPrice }
                }

                GIFT -> {
                    discountInfo.compute(it.type.event) { _, price -> (price ?: INIT_VALUE) + it.discountPrice }
                }
            }
        }

        return discountInfo

    }

    private fun calculateTotalEventDiscount(): Int {
        var totalPrice = INIT_VALUE
        discountInfo.forEach {
            totalPrice += it.value
        }

        return totalPrice
    }

    companion object {
        const val INIT_VALUE = 0
    }


}