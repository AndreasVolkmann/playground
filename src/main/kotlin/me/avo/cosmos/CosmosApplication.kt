package me.avo.cosmos

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class CosmosApplication

fun main(args: Array<String>) {
    runApplication<CosmosApplication>(*args)
}
