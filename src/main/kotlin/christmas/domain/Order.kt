package christmas.domain

import christmas.Constant.MAX_ORDER_SIZE
import christmas.Constant.MENU_DELIMITERS
import christmas.Constant.ORDER_DELIMITERS
import christmas.Constant.ORDER_FORM
import christmas.Constant.ZERO
import christmas.view.OutputView

enum class Menu(val menuName: String, val price: Int, val type: MenuType) {
    MUSHROOM_SOUP("양송이 수프", 6_000, MenuType.APPETIZER), TAPAS("타파스", 5_500, MenuType.APPETIZER), CAESAR_SALAD(
        "시저샐러드",
        8_000,
        MenuType.APPETIZER
    ),

    T_BONE_STAKE("티본스테이크", 55_000, MenuType.MAIN), BBQ_RIB("바비큐립", 54_000, MenuType.MAIN), SEAFOOD_PASTA(
        "해산물파스타",
        35_000, MenuType.MAIN
    ),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, MenuType.MAIN),

    CHOCOLATE_CAKE("초코케이크", 15_000, MenuType.DESSERT), ICE_CREAM("아이스크림", 5_000, MenuType.DESSERT),

    ZERO_COKE("제로콜라", 3_000, MenuType.BEVERAGE), RED_WINE("레드와인", 60_000, MenuType.BEVERAGE), CHAMPAGNE(
        "샴페인",
        25_000,
        MenuType.BEVERAGE
    )
}

enum class MenuType {
    APPETIZER, MAIN, DESSERT, BEVERAGE
}

enum class ErrorMenu(val message: String) {
    ORDER("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ONLY_BEVERAGE("[ERROR] 음로만 주문 시, 주문할 수 없습니다."),
    MAX_ORDER("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.")
}

// validator

fun getMenu(order: String): Map<String, Int> {
    checkOrder(order)
    val orders = order.split(ORDER_DELIMITERS)
    val orderList = mutableMapOf<String, Int>()

    checkOrderMenu(orders, orderList)

    checkOrderList(orderList)

    return orderList
}


private fun orderIsNotOnMenu(order: String) {
    require(Menu.entries.any { it.menuName == order }) { ErrorMenu.ORDER.message }
}

private fun orderNumberIsNotInt(number: String) {
    require(number.all { it.isDigit() }) { ErrorMenu.ORDER.message }
}

private fun orderNumberIsZero(number: Int) {
    require(number != ZERO) { ErrorMenu.ORDER.message }
}

private fun orderDuplicated(existingOrder: Map<String, Int>, newOlder: String) {
    require(!existingOrder.containsKey(newOlder)) { ErrorMenu.ORDER.message }
}

private fun checkOrder(order: String) {
    orderEmpty(order)
    orderOutOfForm(order)
}

private fun orderOutOfForm(order: String) {
    val form = Regex(ORDER_FORM)
    require(form.matches(order)) { ErrorMenu.ORDER.message }
}

private fun orderEmpty(order: String) {
    require(order.isNotEmpty() && order.isNotBlank()) { ErrorMenu.ORDER.message }
}


private fun checkOrderMenu(
    orders: List<String>,
    orderList: MutableMap<String, Int>
) {
    for (menu in orders) {
        val splitMenu = menu.split(MENU_DELIMITERS)
        val menuName = splitMenu[0]
        val menuCount = splitMenu[1]
        OutputView().printMenu(menuName, menuCount)
        orderIsNotOnMenu(menuName)
        orderNumberIsNotInt(menuCount)
        orderNumberIsZero(menuCount.toInt())
        orderDuplicated(orderList, menuName)
        orderList[menuName] = menuCount.toInt()
    }
    println()
}

private fun checkOrderList(orderList: MutableMap<String, Int>) {
    orderOnlyBeverage(orderList)
    orderExceeded(orderList)
}


private fun orderOnlyBeverage(orderList: Map<String, Int>) {
    val menuTypes = mutableSetOf<MenuType>()

    for (order in orderList) {
        val menu = Menu.entries.find { it.menuName == order.key }
        menu?.let {
            menuTypes.add(it.type)
        }
    }

    require(!menuTypes.all { it == MenuType.BEVERAGE }) { ErrorMenu.ONLY_BEVERAGE.message }
}

private fun orderExceeded(orderList: Map<String, Int>) {
    var count = ZERO

    for (order in orderList) {
        count += order.value
    }

    require(count <= MAX_ORDER_SIZE) { ErrorMenu.MAX_ORDER.message }

}



