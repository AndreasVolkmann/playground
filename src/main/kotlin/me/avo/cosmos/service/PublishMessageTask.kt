package me.avo.cosmos.service

import me.avo.cosmos.entity.Channel

interface PublishMessageTask {

    fun publish(channel: Channel)
}