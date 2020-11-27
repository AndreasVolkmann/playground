package me.avo.cosmos.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before

@Aspect
class TestAspect {

//    @Before("execution(private * *(..)) && within(me.avo.cosmos.service..*)")
//    fun logBefore(joinPoint: JoinPoint) {
//        val signature = joinPoint.signature
//        println(signature.name)
//    }
}