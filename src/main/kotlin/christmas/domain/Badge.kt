package christmas.domain

import christmas.Constant.SANTA_THRESHOLD
import christmas.Constant.STAR_THRESHOLD
import christmas.Constant.TREE_THRESHOLD

enum class Badge(val badgeName: String) {
    STAR("별"), TREE("트리"), SANTA("산타"), NONE("없음")
}

fun getBadge(eventDiscount: Int): Badge {
    if (eventDiscount >= SANTA_THRESHOLD) {
        return Badge.SANTA
    }

    if (eventDiscount >= TREE_THRESHOLD) {
        return Badge.TREE
    }

    if (eventDiscount >= STAR_THRESHOLD) {
        return Badge.STAR
    }

    return Badge.NONE
}