package me.avo.cosmos.service

import me.avo.cosmos.platform.feature.FeatureSwitch
import me.avo.cosmos.platform.feature.SampleServiceFeature
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class SampleScheduledService {

    var didRun: Boolean = false

    @FeatureSwitch(SampleServiceFeature)
    @Scheduled(fixedRateString = "\${${SampleServiceFeature}.scheduleRate}")
    fun run() {
        println("Running service!")
        didRun = true
    }

    @FeatureSwitch("SamplePublic")
    fun publicRun() {
        didRun = true
    }

    fun reset() {
        didRun = false
    }
}