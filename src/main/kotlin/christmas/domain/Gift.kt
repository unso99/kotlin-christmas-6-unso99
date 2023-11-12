package christmas.domain

class Gift(price: Int) {
    private var hasGift = false

    init {
        checkGift(price)
    }


    fun getHasGift() = hasGift
    private fun checkGift(price: Int) {
        if (price >= GIFT_THRESHOLD) {
            hasGift = true
            println(GIFT)
            println()
            return
        }

        println(NONE)
        println()
    }

    companion object {
        const val GIFT_THRESHOLD = 120_000
        const val GIFT = "샴페인 1개"
        const val NONE = "없음"
    }
}