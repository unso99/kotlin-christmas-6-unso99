package christmas.domain

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
    orderEmpty(order)
    orderOutOfForm(order)
    val orders = order.split(",")
    val orderList = mutableMapOf<String, Int>()

    for (menu in orders) {
        val splitMenu = menu.split("-")
        println("${splitMenu[0]} ${splitMenu[1]}개")
        orderIsNotOnMenu(splitMenu[0])
        orderNumberIsNotInt(splitMenu[1])
        orderNumberIsZero(splitMenu[1].toInt())
        orderDuplicated(orderList, splitMenu[0])
        orderList[splitMenu[0]] = splitMenu[1].toInt()
    }
    println()

    orderOnlyBeverage(orderList)
    orderExceeded(orderList)

    return orderList
}


fun orderEmpty(order: String) {
    require(order.isNotEmpty() && order.isNotBlank()) { ErrorMenu.ORDER.message }
}

fun orderOutOfForm(order: String) {
    val form = Regex("[^,]+-\\d+(,[^,]+-\\d+)*")
    require(form.matches(order)) { ErrorMenu.ORDER.message }
}

fun orderIsNotOnMenu(order: String) {
    require(Menu.entries.any { it.menuName == order }) { ErrorMenu.ORDER.message }
}

fun orderNumberIsNotInt(number: String) {
    require(number.all { it.isDigit() }) { ErrorMenu.ORDER.message }
}

fun orderNumberIsZero(number: Int) {
    require(number != 0) { ErrorMenu.ORDER.message }
}

fun orderDuplicated(existingOrder: Map<String, Int>, newOlder: String) {
    require(!existingOrder.containsKey(newOlder)) { ErrorMenu.ORDER.message }
}


fun orderOnlyBeverage(orderList: Map<String, Int>) {
    val menuTypes = mutableSetOf<MenuType>()

    for (order in orderList) {
        val menu = Menu.entries.find { it.menuName == order.key }
        menu?.let {
            menuTypes.add(it.type)
        }
    }

    require(!menuTypes.all { it == MenuType.BEVERAGE }) { ErrorMenu.ONLY_BEVERAGE.message }
}

fun orderExceeded(orderList: Map<String, Int>) {
    var count = 0

    for (order in orderList) {
        count += order.value
    }

    require(count <= 20) { ErrorMenu.MAX_ORDER.message }

}