package me.avo.cosmos.platform.feature

interface FeatureSwitchManager {

    fun isEnabled(feature: String): Boolean
}