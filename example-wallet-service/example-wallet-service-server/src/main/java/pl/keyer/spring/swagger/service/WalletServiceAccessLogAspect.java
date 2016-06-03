package pl.keyer.spring.swagger.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WalletServiceAccessLogAspect {
    @Value("${wallet.service.logMessage}")
    private String logMessage;

    @Before("execution(* pl.keyer.spring.swagger.service.WalletServiceRestController.*(..))")
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();

        System.out.printf("%s: %s%n", logMessage, methodName);
    }
}
