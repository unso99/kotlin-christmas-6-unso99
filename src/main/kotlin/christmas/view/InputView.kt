package christmas.view

import camp.nextstep.edu.missionutils.Console

enum class InputMessage(val message: String) {
    VISIT_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ORDER("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
}

class InputView {

    fun readVisitDate(): String {
        println(InputMessage.VISIT_DATE.message)

        return Console.readLine()
    }

    fun readOrder(): String {
        println(InputMessage.ORDER.message)

        return Console.readLine()
    }
}