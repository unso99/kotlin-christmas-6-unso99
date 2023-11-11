package christmas

import camp.nextstep.edu.missionutils.Console

fun main() {
    try {
        printStartMessage()
        printVisitDate()
        val input = inputMessage().trim()
        visitDateEmpty(input)
        visitDateIsNotInt(input)
        visitDateIsNotInRange(input.toInt())
    } catch (e: IllegalArgumentException) {
        println(e.message)
        main()
    }

}

fun inputMessage(): String = Console.readLine()

fun printStartMessage() {
    println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
}

fun printVisitDate() {
    println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
}

fun printOrder() {
    println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1) 티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1")
}

fun printEventPreView(visitDate: String) {
    println("${visitDate}에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
}

fun printOrderMenu() {
    println("<주문 메뉴>")
}

fun printTotalPriceBeforeDiscount() {
    println("<할인 전 총주문 금액>")
}

fun printGiftMenu() {
    println("<증정 메뉴>")
}

fun printBenefitDetails() {
    println("<혜택 내역>")
}

fun printTotalBenefit() {
    println("<총혜택 금액>")
}

fun printExpectedPrice() {
    println("<할인 후 예상 결제 금액>")
}

fun printEventBadge() {
    println("<12월 이벤트 배지>")
}

//validator
// 방문 날짜가 정수가 아닌경우
fun visitDateIsNotInt(date: String) {
    require(date.all { it.isDigit() }) { "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요." }
}

// 방문 날짜가 공백인 경우
fun visitDateEmpty(date: String) {
    require(date.isNotEmpty() && date.isNotBlank()) { "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요." }
}

// 방문 날짜가 1 이상 31이하의 숫자가 아닌 경우
fun visitDateIsNotInRange(date: Int) {
    require(date in 1..31) { "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요." }
}








