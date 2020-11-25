package me.avo.cosmos.repository

import com.microsoft.azure.spring.data.cosmosdb.repository.CosmosRepository
import me.avo.cosmos.entity.Subscriber
import me.avo.cosmos.enum.SubscriptionStatus
import org.springframework.stereotype.Repository

@Repository
interface SubscriberRepository : CosmosRepository<Subscriber, String> {
    fun findByStatus(status: SubscriptionStatus): Collection<Subscriber>
}