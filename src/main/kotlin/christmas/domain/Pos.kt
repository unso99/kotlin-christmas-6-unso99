package christmas.domain

import java.text.NumberFormat
import java.util.*

class Pos(private val orderList: Map<String, Int>) {

    init {
        orderOnlyBeverage(orderList)
        orderExceeded(orderList)
        showTotalPrice(calculateTotalPrice(orderList))

    }

    private fun calculateTotalPrice(orderList: Map<String, Int>): Int {
        var totalPrice = 0

        for (order in orderList) {
            val menu = Menu.entries.find { it.menuName == order.key }
            menu?.let {
                totalPrice += it.price
            }
        }

        return totalPrice
    }

    private fun showTotalPrice(price: Int) {
        println("${"%,d".format(price)}원")
    }


}