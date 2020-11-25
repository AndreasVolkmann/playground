package me.avo.cosmos.entity

import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document
import me.avo.cosmos.enum.SubscriptionStatus
import org.springframework.data.annotation.Id
import java.util.*

@Document(collection = "SubscriberCollection")
data class Subscriber(

    @Id
    var id: String,

    var status: SubscriptionStatus
)