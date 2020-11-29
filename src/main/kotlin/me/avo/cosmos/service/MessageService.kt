package me.avo.cosmos.service

import me.avo.cosmos.platform.feature.FeatureSwitch
import me.avo.cosmos.platform.feature.MessageServiceFeature
import me.avo.cosmos.repository.ChannelRepository
import me.avo.cosmos.util.logger
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class MessageService(
    private val publishMessageTask: PublishMessageTask,
    private val channelRepository: ChannelRepository
) {
    private val logger = logger()

    @FeatureSwitch(MessageServiceFeature)
    @Scheduled(fixedRateString = "\${${MessageServiceFeature}.scheduleRate}")
    fun run() {
        logger.info("Running message service!")

        getChannels().forEach(publishMessageTask::publish)
    }

    private fun getChannels() = channelRepository.findAll()
}