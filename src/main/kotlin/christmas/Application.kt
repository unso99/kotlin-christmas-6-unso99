package christmas

import christmas.domain.*
import christmas.view.InputView
import christmas.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    try {
        outputView.printStartMessage()
        val input = inputView.readVisitDate()
        val visitDate = validVisitDate(input)
        val input2 = inputView.readOrder()
        outputView.printEventPreView(visitDate)
        outputView.printOrderMenu()
        val orderList = getMenu(input2)
        outputView.printTotalPriceBeforeDiscount()
        val pos = Pos(orderList)
        outputView.printGiftMenu()
        val gift = Gift(pos.getTotalPrice())
        outputView.printBenefitDetails()
        val eventCalendar = EventCalendar(visitDate, gift.getHasGift())
        pos.showEventDiscount(eventCalendar.getDiscountInfo())
        outputView.printTotalBenefit()
        pos.showTotalEventDiscount()
        outputView.printExpectedPrice()
        pos.showExpectedPrice()
        outputView.printEventBadge()
        val badge = getBadge(pos.getEventDiscount())
        println(badge.badgeName)


    } catch (e: IllegalArgumentException) {
        println(e.message)
        main()
    }

}









