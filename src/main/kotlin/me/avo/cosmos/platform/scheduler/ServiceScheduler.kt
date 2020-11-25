package me.avo.cosmos.platform.scheduler

import me.avo.cosmos.util.logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import org.springframework.stereotype.Component

@Component
class ServiceScheduler(
    private val scheduler: ThreadPoolTaskScheduler,
    private val environment: Environment,
    @Value("\${defaultScheduleRate:10000}")
    private val defaultScheduleRate: Long,
    scheduledServices: List<ScheduledService>
) {
    private val logger = logger()

    init {
        scheduledServices.forEach(::schedule)
    }

    private fun schedule(service: ScheduledService) {
        val key = "${service.scheduleKey}.scheduleRate"
        val period = environment[key]?.toLong() ?: defaultScheduleRate
        logger.info("Scheduling ${service::class.simpleName} @fixedRate $period ms")
        scheduler.scheduleAtFixedRate(service::run, period)
    }
}
