package ru.mobileup.template.features.coin.domain

data class DetailedCoin(
    val id: CoinId,
    val name: String,
    val image: String,
    val description: String,
    val categories: List<String>
) {
    companion object {
        val MOCK = DetailedCoin(
            id = CoinId("bitcoin"),
            name = "Bitcoin",
            image = "",
            description = "",
            categories = listOf("Category 1", "Category 2", "Category 3")
        )
    }

}
