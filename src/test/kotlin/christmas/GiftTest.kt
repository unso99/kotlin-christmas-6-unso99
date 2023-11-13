package christmas

import christmas.domain.Gift
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class GiftTest {
    private lateinit var gift : Gift


    @ParameterizedTest
    @ValueSource(ints = [0,1_000,10_000,100_000,119_999])
    fun `GIFT_THRESHOLD를 넘기지 못하면 증정 이벤트를 받지 못함`(value : Int){
        gift = Gift(value)
        val hasGift = gift.getHasGift()

        val expected = false

        assertEquals(expected,hasGift)
    }

    @ParameterizedTest
    @ValueSource(ints = [120_000,120_001,1_000_000])
    fun `GIFT_THRESHOLD를 넘기면 증정 이벤트를 받음`(value : Int){
        gift = Gift(value)
        val hasGift = gift.getHasGift()

        val expected = true

        assertEquals(expected,hasGift)
    }

}