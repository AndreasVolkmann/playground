package me.avo.cosmos.platform.feature

/**
 * The execution of methods marked with this annotation is controlled by a feature switch.
 * The [me.avo.cosmos.aspect.FeatureAspect] controls the behavior.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class FeatureSwitch(
    val name: String
)