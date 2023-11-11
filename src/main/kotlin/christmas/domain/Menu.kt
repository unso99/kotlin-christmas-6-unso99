package christmas.domain

enum class Menu(val menuName: String, val price: Int) {
    MUSHROOM_SOUP("양송이 수프", 6_000), TAPAS("타파스", 5_500), CAESAR_SALAD("시저샐러드", 8_000),

    T_BONE_STAKE("티본스테이크", 55_000), BBQ_RIB("바비큐립", 54_000), SEAFOOD_PASTA(
        "해산물파스타",
        35_000
    ),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000),

    CHOCOLATE_CAKE("초코케이크", 15_000), ICE_CREAM("아이스크림", 5_000),

    ZERO_COKE("제로콜라", 3_000), RED_WINE("레드와인", 60_000), CHAMPAGNE("샴페인", 25_000)
}

enum class MenuBoard(val menu: List<Menu>) {
    APPETIZER(listOf(Menu.MUSHROOM_SOUP, Menu.TAPAS, Menu.CAESAR_SALAD)),
    MAIN(listOf(Menu.T_BONE_STAKE, Menu.BBQ_RIB, Menu.SEAFOOD_PASTA, Menu.CHRISTMAS_PASTA)),
    DESSERT(listOf(Menu.CHOCOLATE_CAKE, Menu.ICE_CREAM)),
    BEVERAGE(listOf(Menu.ZERO_COKE, Menu.RED_WINE, Menu.CHAMPAGNE))
}

enum class ErrorMenu(val message: String) {
    ERROR("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
}

// validator

fun getMenu(order: String): Map<String, Int> {
    orderEmpty(order)
    orderOutOfForm(order)
    val orders = order.split(",")
    val orderList = mutableMapOf<String, Int>()

    for (menu in orders) {
        val splitMenu = menu.split("-")
        orderIsNotOnMenu(splitMenu[0])
        orderList[splitMenu[0]] = splitMenu[1].toInt()
    }

    return orderList
}


//입력이 공백
fun orderEmpty(order: String) {
    require(order.isNotEmpty() && order.isNotBlank()) { ErrorMenu.ERROR.message }
}

//메뉴형식
fun orderOutOfForm(order: String) {
    val form = Regex("[^,]+-\\d+(,[^,]+-\\d+)*")
    require(form.matches(order)) { ErrorMenu.ERROR.message }
}

fun orderIsNotOnMenu(menu: String) {
    require(Menu.entries.any { it.menuName == menu }) { ErrorMenu.ERROR.message }
}
