package me.avo.cosmos.config

import org.springframework.aop.Advisor
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import org.springframework.aop.interceptor.CustomizableTraceInterceptor
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LoggerConfig {

    @Bean fun customizableTraceInterceptor() = CustomizableTraceInterceptor().apply {
        setUseDynamicLogger(true)
        setEnterMessage("Entering $[methodName]($[arguments])")
        setExitMessage("Leaving $[methodName](), returned $[returnValue], running time = $[invocationTime] ms")
        setExceptionMessage("Error $[methodName](), exception $[exception], running time = $[invocationTime] ms")
    }

    @Bean fun loggingAdvisor(): Advisor {
        val pointcut = AspectJExpressionPointcut().apply {
            expression = """
                execution (* (@org.springframework.stereotype.Service *).*(..))
            """.trimIndent()
        }
        return DefaultPointcutAdvisor(pointcut, customizableTraceInterceptor())
    }
}
