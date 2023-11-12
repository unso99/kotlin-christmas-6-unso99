package christmas.domain

import christmas.Constant.EVENT_END_DATE
import christmas.Constant.EVENT_START_DATE

enum class VisitDate(val message: String) {
    ERROR("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
}

fun validVisitDate(date: String): Int {
    visitDateEmpty(date)
    visitDateIsNotInt(date)
    visitDateIsNotInRange(date.toInt())
    return date.toInt()
}

fun visitDateEmpty(date: String) {
    require(date.isNotEmpty() && date.isNotBlank()) { VisitDate.ERROR.message }
}

fun visitDateIsNotInt(date: String) {
    require(date.all { it.isDigit() }) { VisitDate.ERROR.message }
}

fun visitDateIsNotInRange(date: Int) {
    require(date in EVENT_START_DATE..EVENT_END_DATE) { VisitDate.ERROR.message }
}
