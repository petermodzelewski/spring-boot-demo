package pl.keyer.spring.daemon;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@EnableDiscoveryClient
public class ScheduledTasks {
    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    @Autowired
    private CounterService counterService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 200)
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 100L, multiplier = 2, maxDelay = 1000))
    public void reportCurrentTime() {
        String date = dateFormat.format(new Date());
        System.out.println("The time is now " + date);

        Greeting response = restTemplate.getForObject("http://GREETINGS-SERVICE/greeting?name={name}", Greeting.class, date);

        System.out.println(response);
    }
}
