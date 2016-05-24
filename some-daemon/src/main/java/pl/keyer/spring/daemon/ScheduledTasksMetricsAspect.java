package pl.keyer.spring.daemon;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ScheduledTasksMetricsAspect {
    private final CounterService counterService;

    @Autowired
    public ScheduledTasksMetricsAspect(CounterService counterService) {
        this.counterService = counterService;
    }

    @AfterThrowing(pointcut = "execution(* pl.keyer.spring.daemon.ScheduledTasks.reportCurrentTime())", throwing = "e")
    public void afterMethodThrowsException(Exception e) {
        counterService.increment("test.errors");
    }

    @AfterReturning(pointcut = "execution(* pl.keyer.spring.daemon.ScheduledTasks.reportCurrentTime())")
    public void afterMethodFinishes() {
        counterService.increment("test.success");
    }
}
