package me.avo.cosmos.service

import me.avo.cosmos.platform.feature.FeatureSwitch
import me.avo.cosmos.platform.scheduler.ScheduledService
import org.springframework.stereotype.Service

@Service
class SampleScheduledService : ScheduledService {

    var didRun: Boolean = false

    @FeatureSwitch("SampleService")
    override fun run() {
        println("Running service!")
        didRun = true
    }

    fun reset() {
        didRun = false
    }

    fun runPrivate() {
        privateRun()
    }

    @FeatureSwitch("SamplePrivate")
    private fun privateRun() {
        didRun = true
    }

    @FeatureSwitch("SamplePublic")
    fun publicRun() {
        didRun = true
    }
}