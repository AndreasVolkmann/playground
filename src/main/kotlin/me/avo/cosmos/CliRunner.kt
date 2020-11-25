package me.avo.cosmos

import me.avo.cosmos.entity.Channel
import me.avo.cosmos.entity.Message
import me.avo.cosmos.entity.Subscriber
import me.avo.cosmos.enum.SubscriptionStatus
import me.avo.cosmos.repository.ChannelRepository
import me.avo.cosmos.repository.MessageRepository
import me.avo.cosmos.repository.SubscriberRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class CliRunner(
    private val messageRepository: MessageRepository,
    private val channelRepository: ChannelRepository,
    private val subscriberRepository: SubscriberRepository
) : CommandLineRunner {

    override fun run(vararg args: String) {
        val subscriberA = Subscriber("s1", SubscriptionStatus.Active).let(subscriberRepository::save)
        val subscriberB = Subscriber("s2", SubscriptionStatus.Active).let(subscriberRepository::save)
        val channelA = Channel("ch1", listOf(subscriberA.id, subscriberB.id)).let(channelRepository::save)
        val channelB = Channel("ch2", listOf(subscriberB.id)).let(channelRepository::save)
        Message("m1", "Important!", channelA.id).let(messageRepository::save)
        Message("m2", "Exclusive!", channelB.id).let(messageRepository::save)
    }
}