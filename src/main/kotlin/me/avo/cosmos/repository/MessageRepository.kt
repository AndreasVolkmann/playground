package me.avo.cosmos.repository

import com.microsoft.azure.spring.data.cosmosdb.repository.CosmosRepository
import me.avo.cosmos.entity.Message
import me.avo.cosmos.enum.MessagePublishStatus
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : CosmosRepository<Message, String> {

    fun findByChannelIdAndStatus(
        channelId: String,
        status: MessagePublishStatus
    ): Collection<Message>
}