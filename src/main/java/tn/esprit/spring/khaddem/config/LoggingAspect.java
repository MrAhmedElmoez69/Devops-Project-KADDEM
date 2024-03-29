/* This is a commented-out code block
package tn.esprit.spring.khaddem.config;

import lombok.extern.slf4j.log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf
public class LoggingAspect {

    @Before("execution(* tn.esprit.spring.khaddem.services.*.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("In method : " + name + " : ");
    }

    @AfterReturning("execution( * tn.esprit.spring.khaddem.services.DepartementServiceImpl.retrieveDepartement(..))")
    public void logMethodExit1(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("Out of method without errors : " + name );
    }

    @AfterReturning("execution( * tn.esprit.spring.khaddem.services.UniversiteServiceImpl.retrieveUniversite(..))")
    public void logMethodExit3(JoinPoint joinPoint) {
        String name1 = joinPoint.getSignature().getName();
        log.info("Out of method without errors : " + name1 );
    }

    @AfterThrowing("execution(* tn.esprit.spring.khaddem.services.*.*(..))")
    public void logMethodExit2(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.error("Out of method with erros : " + name );
    }

    @After("execution(* tn.esprit.spring.khaddem.services.*.*(..))")
    public void logMethodExit(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("Out of method : " + name );
    }
}
*/
