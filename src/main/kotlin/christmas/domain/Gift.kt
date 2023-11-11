package christmas.domain

class Gift(private val price : Int) {

    init {
        checkGift(price)
    }

    private fun checkGift(price : Int){
        if(price >= 120_000) {
            println("샴페인 1개")
            return
        }

        println("없음")
        println()
    }
}