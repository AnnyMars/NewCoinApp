package ru.mobileup.template.features.coins.data

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import ru.mobileup.template.features.coins.data.dto.CoinByIdResponse
import ru.mobileup.template.features.coins.data.dto.CoinsResponse

interface CoinApi {

    @GET("coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") vsCurrency: String? = null,
        @Query("per_page") perPage: Int = 30,
        @Query("page") page: Int = 1,
    ): List<CoinsResponse>

    @GET("coins/{id}")
    suspend fun getCoinById(
        @Path("id") id: String
    ): CoinByIdResponse
}