package ru.mobileup.template.features.coin.domain

data class DetailedCoin(
    val id: String,
    val name: String,
    val image: String,
    val description: String,
    val categories: List<String>
)
