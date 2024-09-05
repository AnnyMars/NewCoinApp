package ru.mobileup.template.features.coin.data

import me.aartikov.replica.keyed.KeyedReplica
import ru.mobileup.template.features.coin.domain.Coin
import ru.mobileup.template.features.coin.domain.CoinId
import ru.mobileup.template.features.coin.domain.Currency
import ru.mobileup.template.features.coin.domain.DetailedCoin

interface CoinRepository {

    val coinList: KeyedReplica<Currency, List<Coin>>

    val coinById: KeyedReplica<CoinId, DetailedCoin>
}