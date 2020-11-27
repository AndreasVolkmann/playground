package me.avo.cosmos.aspect

import me.avo.cosmos.service.SampleScheduledService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import strikt.api.expectThat
import strikt.assertions.isFalse
import strikt.assertions.isTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
internal class FeatureAspectTest(
    @Autowired private val sampleScheduledService: SampleScheduledService
) {

    @BeforeEach
    fun beforeEach() {
        sampleScheduledService.reset()
    }

    @Test fun `disabled feature should not execute`() {
        sampleScheduledService.run()
        expectThat(sampleScheduledService.didRun).isFalse()
    }

    @Test fun `private disabled feature should not execute`() {
        sampleScheduledService.runPrivate()
        expectThat(sampleScheduledService.didRun).isFalse()
    }

    @Test fun `public enabled feature should execute`() {
        sampleScheduledService.publicRun()
        expectThat(sampleScheduledService.didRun).isTrue()
    }

}