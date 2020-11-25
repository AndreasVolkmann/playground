package me.avo.cosmos.repository

import com.microsoft.azure.spring.data.cosmosdb.repository.CosmosRepository
import me.avo.cosmos.entity.Channel
import org.springframework.stereotype.Repository

@Repository
interface ChannelRepository : CosmosRepository<Channel, String> {
}