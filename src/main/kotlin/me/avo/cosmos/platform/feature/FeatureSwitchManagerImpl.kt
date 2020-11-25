package me.avo.cosmos.platform.feature

import me.avo.cosmos.util.logger
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.stereotype.Component

@Component
class FeatureSwitchManagerImpl(
    private val environment: Environment
) : FeatureSwitchManager {
    private val logger = logger()

    /**
     * Checks whether the [feature] is enabled.
     * @param feature the feature to check.
     * @return true when enabled; false when disabled.
     */
    override fun isEnabled(feature: String): Boolean {
        val configKey = "${feature}.enabled"
        val isEnabled = environment[configKey] == "true"
        logger.info("Feature {}, isEnabled: {}", feature, isEnabled)
        return isEnabled
    }
}