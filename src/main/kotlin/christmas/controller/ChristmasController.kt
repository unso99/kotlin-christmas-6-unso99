package christmas.controller

import christmas.domain.*
import christmas.view.InputView
import christmas.view.OutputView

class ChristmasController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun start() {
        outputView.printStartMessage()
        val visitDate = getVisitDate()
        val orderList = getOrderList(visitDate)
        val pos = showTotalPriceBeforeDiscount(visitDate, orderList)
        val gift = showGift(pos)
        showDiscountDetails(visitDate, gift, pos)
        showTotalDiscount(pos)
        showExpectedPrice(pos, gift)
        showBadge(pos)
    }


    private fun getVisitDate(): Int {
        var input = ""
        return try {
            input = inputView.readVisitDate()
            validVisitDate(input)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getVisitDate()
        }
    }

    private fun getOrderList(visitDate: Int): Map<String, Int> {
        var order = ""
        return try {
            order = inputView.readOrder()
            outputView.printEventPreView(visitDate)
            outputView.printOrderMenu()
            getMenu(order)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getOrderList(visitDate)
        }
    }

    private fun showTotalPriceBeforeDiscount(visitDate: Int, orderList: Map<String, Int>): Pos {
        try {
            outputView.printTotalPriceBeforeDiscount()
            return Pos(orderList)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getOrderList(visitDate)
        }
        return Pos(orderList)
    }

    private fun showGift(pos: Pos): Gift {
        outputView.printGiftMenu()
        return Gift(pos.getTotalPrice())
    }

    private fun showDiscountDetails(visitDate: Int, gift: Gift, pos: Pos) {
        outputView.printBenefitDetails()
        val eventCalendar = EventCalendar(visitDate, gift.getHasGift())
        pos.showEventDiscount(eventCalendar.getDiscountInfo())
    }

    private fun showTotalDiscount(pos: Pos) {
        outputView.printTotalBenefit()
        pos.showTotalEventDiscount()
    }

    private fun showExpectedPrice(pos: Pos, gift: Gift) {
        outputView.printExpectedPrice()
        val expectedPrice = pos.getExpectedPrice(gift.getHasGift())

        outputView.printPrice(expectedPrice)
    }

    private fun showBadge(pos: Pos) {
        outputView.printEventBadge()
        val badge = getBadge(pos.getEventDiscount())
        outputView.printBadge(badge)
    }
}