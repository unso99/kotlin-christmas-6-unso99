package christmas.domain

enum class Badge(val badgeName: String) {
    STAR("별"), TREE("트리"), SANTA("산타"), NONE("없음")
}

fun getBadge(eventDiscount: Int): Badge {
    if (eventDiscount >= 20_000) {
        return Badge.SANTA
    }

    if (eventDiscount >= 10_000) {
        return Badge.TREE
    }

    if (eventDiscount >= 5_000) {
        return Badge.STAR
    }

    return Badge.NONE
}