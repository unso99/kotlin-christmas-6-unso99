package christmas.domain

import christmas.domain.DiscountType.*

class Pos(private val orderList: Map<String, Int>) {
    private val orderMenuTypes = mutableMapOf(
        MenuType.APPETIZER to 0,
        MenuType.MAIN to 0,
        MenuType.DESSERT to 0,
        MenuType.BEVERAGE to 0
    )

    private val discountInfo = mutableMapOf<String, Int>()

    init {
        orderOnlyBeverage(orderList)
        orderExceeded(orderList)
        countMenuType()
        showTotalPrice(calculateTotalPrice(orderList))

    }

    fun getTotalPrice(): Int = calculateTotalPrice(orderList)

    fun showEventDiscount(discount: List<Discount>) {
        val eventDiscount = calculateEventDiscount(discount)

        eventDiscount.forEach {
            println("${it.key}-${"%,d".format(it.value)}원")
        }
        println()
    }

    fun showTotalEventDiscount(){
        val totalDiscount = calculateTotalEventDiscount()
        println("-${"%,d".format(totalDiscount)}원")
        println()
    }

    private fun countMenuType() {
        for (order in orderList) {
            val menu = Menu.entries.find { it.menuName == order.key }
            menu?.let {
                orderMenuTypes.compute(it.type) { _, count -> (count ?: 0) + order.value }
            }
        }
    }

    private fun calculateTotalPrice(orderList: Map<String, Int>): Int {
        var totalPrice = 0

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
                    discountInfo.compute(it.type.event) { _, price -> (price ?: 0) + it.discountPrice }
                }

                WEEKDAY -> {
                    val dessertCount = orderMenuTypes[MenuType.DESSERT]!!
                    discountInfo.compute(it.type.event) { _, price -> (price ?: 0) + it.discountPrice * dessertCount }
                }

                WEEKEND -> {
                    val mainCount = orderMenuTypes[MenuType.MAIN]!!
                    discountInfo.compute(it.type.event) { _, price -> (price ?: 0) + it.discountPrice * mainCount }
                }

                SPECIAL -> {
                    discountInfo.compute(it.type.event) { _, price -> (price ?: 0) + it.discountPrice }
                }

                GIFT -> {
                    discountInfo.compute(it.type.event) { _, price -> (price ?: 0) + it.discountPrice }
                }
            }
        }

        return discountInfo

    }

    private fun calculateTotalEventDiscount(): Int {
        var totalPrice = 0
        discountInfo.forEach {
            totalPrice += it.value
        }

        return totalPrice
    }


}