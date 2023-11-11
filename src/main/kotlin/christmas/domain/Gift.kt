package christmas.domain

class Gift(private val price : Int) {
    private var hasGift = false
    init {
        checkGift(price)
    }


    fun getHasGift() = hasGift
    private fun checkGift(price : Int){
        if(price >= 120_000) {
            hasGift = true
            println("샴페인 1개")
            println()
            return
        }

        println("없음")
        println()
    }
}