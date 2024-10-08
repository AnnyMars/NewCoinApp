package ru.mobileup.template.features.coin.data

import me.aartikov.replica.keyed.KeyedReplica
import ru.mobileup.template.features.coin.domain.Coin
import ru.mobileup.template.features.coin.domain.CoinId
import ru.mobileup.template.core.common_domain.Currency
import ru.mobileup.template.features.coin.domain.DetailedCoin

interface CoinRepository {

    val coinListReplica: KeyedReplica<Currency, List<Coin>>

    val coinDetailsReplica: KeyedReplica<CoinId, DetailedCoin>
}