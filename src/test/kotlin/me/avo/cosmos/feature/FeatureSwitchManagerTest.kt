package me.avo.cosmos.feature

import me.avo.cosmos.platform.feature.FeatureSwitchManager
import me.avo.cosmos.platform.feature.MessageServiceFeature
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import strikt.api.expectThat
import strikt.assertions.isFalse
import strikt.assertions.isTrue

@SpringBootTest
internal class FeatureSwitchManagerTest(
    @Autowired private val featureSwitchManager: FeatureSwitchManager
) {

    @Test
    fun `enabled feature should return true`() {
        val feature = MessageServiceFeature

        val isEnabled = featureSwitchManager.isEnabled(feature)

        expectThat(isEnabled).isTrue()
    }

    @Test
    fun `disabled feature should return false`() {
        val feature = "TestFeature"

        val isEnabled = featureSwitchManager.isEnabled(feature)

        expectThat(isEnabled).isFalse()
    }

}