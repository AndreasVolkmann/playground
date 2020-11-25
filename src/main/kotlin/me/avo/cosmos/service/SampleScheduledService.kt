package me.avo.cosmos.service

import me.avo.cosmos.platform.feature.FeatureSwitch
import me.avo.cosmos.platform.scheduler.ScheduledService
import org.springframework.stereotype.Service

@Service
class SampleScheduledService : ScheduledService {

    @FeatureSwitch("SampleService")
    override fun run() = println("Running service!")
}