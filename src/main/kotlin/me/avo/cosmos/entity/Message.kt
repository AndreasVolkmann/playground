package me.avo.cosmos.entity

import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document
import me.avo.cosmos.enum.MessagePublishStatus
import org.springframework.data.annotation.Id

@Document(collection = "MessageCollection")
data class Message(
    @Id
    val id: String,

    val message: String,

    val channelId: String,

    var status: MessagePublishStatus = MessagePublishStatus.NotPublished
)