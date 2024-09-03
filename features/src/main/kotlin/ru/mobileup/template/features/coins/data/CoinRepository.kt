package ru.mobileup.template.features.coins.data

import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.single.Replica
import ru.mobileup.template.features.coins.data.dto.CoinByIdResponse
import ru.mobileup.template.features.coins.data.dto.CoinsResponse

interface CoinRepository {

    val coinList: KeyedReplica<String, List<CoinsResponse>>

    val coinById: KeyedReplica<String, CoinByIdResponse>

}