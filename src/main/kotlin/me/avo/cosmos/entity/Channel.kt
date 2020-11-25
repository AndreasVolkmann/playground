package me.avo.cosmos.entity

import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document
import org.springframework.data.annotation.Id

@Document(collection = "ChannelCollection")
class Channel(

    @Id
    val id: String,

    var subscriberIds: Collection<String>
)