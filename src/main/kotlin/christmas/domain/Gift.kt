package christmas.domain

import christmas.view.OutputView

class Gift(price: Int) {
    private var hasGift = false
    private val outputView = OutputView()

    init {
        checkGift(price)
    }


    fun getHasGift() = hasGift
    private fun checkGift(price: Int) {
        if (price >= GIFT_THRESHOLD) {
            hasGift = true
            outputView.printGift()
            outputView.printEmpty()
            return
        }
        outputView.printNone()
        outputView.printEmpty()
    }

    companion object {
        const val GIFT_THRESHOLD = 120_000
    }
}