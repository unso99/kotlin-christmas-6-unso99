package christmas.view

enum class OutputMessage(val message: String) {
    START("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    EVENT_PREVIEW("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU("<주문 메뉴>"),
    TOTAL_PRICE_BEFORE_DISCOUNT("<할인 전 총주문 금액>"),
    GIFT_MENU("<증정 메뉴>"),
    BENEFIT_DETAILS("<혜택 내역>"),
    TOTAL_BENEFIT("<총혜택 금액>"),
    EXPECTED_PRICE("<할인 후 예상 결제 금액>"),
    EVENT_BADGE("<12월 이벤트 배지>")

}

class OutputView {

    fun printStartMessage() {
        println(OutputMessage.START.message)
    }

    fun printEventPreView(visitDate: Int) {
        println(String.format(OutputMessage.EVENT_PREVIEW.message, visitDate))
        println()
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


}