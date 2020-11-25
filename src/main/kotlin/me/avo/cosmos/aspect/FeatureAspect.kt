package me.avo.cosmos.aspect

import me.avo.cosmos.exception.FeatureNameBlankException
import me.avo.cosmos.platform.feature.FeatureSwitch
import me.avo.cosmos.platform.feature.FeatureSwitchManager
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component

@Aspect
@Component
class FeatureAspect(
    private val featureSwitchManager: FeatureSwitchManager
) {

    @Pointcut(value = "execution(public * *(..))")
    fun anyPublicMethod() = Unit

    @Around("anyPublicMethod() && @annotation(annotation)")
    fun proceedIfEnabled(joinPoint: ProceedingJoinPoint, annotation: FeatureSwitch): Any? {
        val featureName = annotation.name.takeIf(String::isNotBlank)
            ?: throw FeatureNameBlankException(joinPoint.signature)

        return when (featureSwitchManager.isEnabled(featureName)) {
            true -> joinPoint.proceed()
            false -> null
        }
    }
}