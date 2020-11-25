package me.avo.cosmos.service

import me.avo.cosmos.entity.Channel
import me.avo.cosmos.entity.Message
import me.avo.cosmos.entity.Subscriber
import me.avo.cosmos.enum.MessagePublishStatus
import me.avo.cosmos.repository.MessageRepository
import me.avo.cosmos.repository.SubscriberRepository
import me.avo.cosmos.util.logger
import org.springframework.stereotype.Service

@Service
class PublishMessageTaskImpl(
    private val messageRepository: MessageRepository,
    private val subscriberRepository: SubscriberRepository
) : PublishMessageTask {

    private val logger = logger()

    override fun publish(channel: Channel) {
        val messages = getUnpublishedMessages(channel.id).onEach(::println)
        val subscribers = subscriberRepository.findAllById(channel.subscriberIds)
        sendMessages(messages, subscribers)
    }

    private fun getUnpublishedMessages(channelId: String) = messageRepository
        .findByChannelIdAndStatus(channelId, MessagePublishStatus.NotPublished)

    private fun sendMessages(messages: Iterable<Message>, subscribers: Iterable<Subscriber>) {
        messages.forEach { message ->
            sendMessage(message, subscribers)
            messageRepository.save(message)
        }
    }

    private fun sendMessage(message: Message, subscribers: Iterable<Subscriber>) =
        try {
            subscribers.forEach { send(message, it) }
            message.status = MessagePublishStatus.Published
            logger.info("Success sending message: $message")
        } catch (ex: Exception) {
            logger.error("Exception sending message: $message", ex)
            message.status = MessagePublishStatus.Failed
        }

    private fun send(message: Message, subscriber: Subscriber) = println("Pushing message $message to $subscriber")
}