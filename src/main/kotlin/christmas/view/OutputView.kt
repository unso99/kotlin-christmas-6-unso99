package christmas.view

import christmas.Constant.GIFT
import christmas.Constant.NONE
import christmas.domain.Badge

enum class OutputMessage(val message: String) {
    START("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    EVENT_PREVIEW("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU("<주문 메뉴>"),
    MENU_ITEM("%s %s개"),
    TOTAL_PRICE_BEFORE_DISCOUNT("<할인 전 총주문 금액>"),
    GIFT_MENU("<증정 메뉴>"),
    BENEFIT_DETAILS("<혜택 내역>"),
    TOTAL_BENEFIT("<총혜택 금액>"),
    EXPECTED_PRICE("<할인 후 예상 결제 금액>"),
    EVENT_BADGE("<12월 이벤트 배지>"),
    EVENT_DISCOUNT("%s-%,d원"),
    MINUS_PRICE("-%,d원"),
    PRICE("%,d원"),

}

class OutputView {

    fun printStartMessage() {
        println(OutputMessage.START.message)
    }

    fun printEventPreView(visitDate: Int) {
        println(String.format(OutputMessage.EVENT_PREVIEW.message, visitDate))
        printEmpty()
    }

    fun printOrderMenu() {
        println(OutputMessage.ORDER_MENU.message)
    }

    fun printTotalPriceBeforeDiscount() {
        println(OutputMessage.TOTAL_PRICE_BEFORE_DISCOUNT.message)
    }

    fun printGiftMenu() {
        println(OutputMessage.GIFT_MENU.message)
    }

    fun printBenefitDetails() {
        println(OutputMessage.BENEFIT_DETAILS.message)
    }

    fun printTotalBenefit() {
        println(OutputMessage.TOTAL_BENEFIT.message)
    }

    fun printExpectedPrice() {
        println(OutputMessage.EXPECTED_PRICE.message)
    }

    fun printEventBadge() {
        println(OutputMessage.EVENT_BADGE.message)
    }

    fun printGift() {
        println(GIFT)
    }

    fun printNone() {
        println(NONE)
    }

    fun printMenu(name: String, count: String) {
        println(String.format(OutputMessage.MENU_ITEM.message, name, count))
    }

    fun printEventDiscount(message: String, price: Int) {
        println(String.format(OutputMessage.EVENT_DISCOUNT.message, message, price))
    }

    fun printTotalEventDiscount(price: Int) {
        if (price != 0) {
            println(String.format(OutputMessage.MINUS_PRICE.message, price))
            printEmpty()
            return
        }
        printPrice(price)
    }

    fun printPrice(price: Int) {
        println(String.format(OutputMessage.PRICE.message, price))
        printEmpty()
    }

    fun printBadge(badge: Badge){
        println(badge.badgeName)
    }

    private fun printEmpty() {
        println()
    }


}