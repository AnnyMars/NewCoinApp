package ru.mobileup.template.features.coin.data

import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.keyed.KeyedReplicaSettings
import me.aartikov.replica.single.ReplicaSettings
import ru.mobileup.template.features.coin.data.dto.CoinByIdResponse.Companion.toDomain
import ru.mobileup.template.features.coin.data.dto.CoinsResponse.Companion.toDomain
import ru.mobileup.template.features.coin.domain.Coin
import ru.mobileup.template.features.coin.domain.CoinId
import ru.mobileup.template.core.common_domain.Currency
import ru.mobileup.template.features.coin.domain.DetailedCoin
import kotlin.time.Duration.Companion.minutes

class CoinRepositoryImpl(
    replicaClient: ReplicaClient,
    api: CoinApi
): CoinRepository {

    override val coinListReplica: KeyedReplica<Currency, List<Coin>> =
        replicaClient.createKeyedReplica(
            name = "allCoins",
            childName = { "vs_currency = $it" },
            childSettings = {
                ReplicaSettings(
                    staleTime = 5.minutes
                )
            },
            fetcher = { currency ->
                api.getCoins(currency.toString()).map { it.toDomain() }
            }
        )

    override val coinDetailsReplica: KeyedReplica<CoinId, DetailedCoin> =
        replicaClient.createKeyedReplica(
            name = "coinById",
            childName = { coinId -> "coinId = ${coinId.value}" },
            settings = KeyedReplicaSettings(maxCount = 5),
            childSettings = {
                ReplicaSettings(staleTime = 5.minutes)
            }
        ){ coinId ->
            api.getCoinById(coinId.value).toDomain()
        }
}