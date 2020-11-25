package me.avo.cosmos.service

import me.avo.cosmos.platform.feature.FeatureSwitch
import me.avo.cosmos.platform.feature.MessageServiceFeature
import me.avo.cosmos.platform.scheduler.ScheduledService
import me.avo.cosmos.repository.ChannelRepository
import me.avo.cosmos.util.logger
import org.springframework.stereotype.Service

@Service
class MessageService(
    private val publishMessageTask: PublishMessageTask,
    private val channelRepository: ChannelRepository
) : ScheduledService {
    private val logger = logger()

    @FeatureSwitch(MessageServiceFeature)
    override fun run() {
        logger.info("Running message service!")

        getChannels().forEach(publishMessageTask::publish)
    }

    private fun getChannels() = channelRepository.findAll()
}