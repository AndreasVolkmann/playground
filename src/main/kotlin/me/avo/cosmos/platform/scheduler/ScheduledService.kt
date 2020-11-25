package me.avo.cosmos.platform.scheduler

interface ScheduledService {
    fun run()

    val scheduleKey: String get() = this::class.simpleName!!
}