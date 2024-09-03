package ru.mobileup.template.features.coins.data

import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.keyed.KeyedReplicaSettings
import me.aartikov.replica.single.ReplicaSettings
import ru.mobileup.template.features.coins.data.dto.CoinByIdResponse
import ru.mobileup.template.features.coins.data.dto.CoinsResponse
import kotlin.time.Duration.Companion.minutes

class CoinRepositoryImpl(
    replicaClient: ReplicaClient,
    api: CoinApi
): CoinRepository {

    override val coinList: KeyedReplica<String, List<CoinsResponse>> =
        replicaClient.createKeyedReplica(
            name = "allCoins",
            childName = { "vs_currency = $it" },
            childSettings = {
                ReplicaSettings(
                    staleTime = 1.minutes
                )
            },
            fetcher = {
                api.getCoins(it)
            }
        )

    override val coinById: KeyedReplica<String, CoinByIdResponse> =
        replicaClient.createKeyedReplica(
            name = "coinById",
            childName = { coinId -> "coinId = $coinId" },
            settings = KeyedReplicaSettings(maxCount = 5),
            childSettings = {
                ReplicaSettings(staleTime = 1.minutes)
            },
            fetcher = {
                api.getCoinById(it)
            }
        )

}