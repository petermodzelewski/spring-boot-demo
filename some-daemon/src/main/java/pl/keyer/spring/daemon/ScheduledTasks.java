package pl.keyer.spring.daemon;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    @Retryable(maxAttempts = 5)
    public void reportCurrentTime() {
        String date = dateFormat.format(new Date());
        System.out.println("The time is now " + date);

        Greeting response = restTemplate.getForObject("http://GREETINGS-SERVICE/greeting?name={name}", Greeting.class, date);

        System.out.println(response);
    }
}
