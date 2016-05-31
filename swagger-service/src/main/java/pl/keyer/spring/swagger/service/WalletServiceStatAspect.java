package pl.keyer.spring.swagger.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WalletServiceStatAspect {
    private final CounterService counterService;

    @Autowired
    public WalletServiceStatAspect(CounterService counterService) {
        this.counterService = counterService;
    }

    @AfterThrowing(pointcut = "execution(* pl.keyer.spring.swagger.service.WalletServiceRestController.*(..))", throwing = "e")
    public void afterMethodThrowsException(JoinPoint joinPoint, Exception e){
        String methodName = joinPoint.getSignature().getName();
        String exceptionType = e.getClass().getSimpleName();

        counterService.increment("meter.nerve.service.wallet.exceptions");
        counterService.increment("meter.nerve.service.wallet." + methodName + ".exceptions");
        counterService.increment("meter.nerve.service.wallet." + methodName + ".exceptions." + exceptionType);
        counterService.increment("meter.nerve.service.wallet.exceptions." + exceptionType);
    }


    @AfterReturning("execution(* pl.keyer.spring.swagger.service.WalletServiceRestController.*(..))")
    public void afterMethodReturns(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();

        counterService.increment("meter.nerve.service.wallet.success");
        counterService.increment("meter.nerve.service.wallet." + methodName + ".success");
    }

    @Before("execution(* pl.keyer.spring.swagger.service.WalletServiceRestController.*(..))")
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();

        counterService.increment("meter.nerve.service.wallet.calls");
        counterService.increment("meter.nerve.service.wallet." + methodName + ".calls");
    }
}
