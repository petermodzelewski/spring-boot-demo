package pl.keyer.spring.daemon.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.keyer.spring.swagger.service.WalletServiceClient;
import pl.keyer.spring.swagger.service.model.Wallet;
import pl.keyer.spring.swagger.service.model.WalletId;

@Component
@EnableDiscoveryClient
public class ScheduledTasks {
    @Autowired
    WalletServiceClient walletServiceClient;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 1000)
    @Retryable(maxAttempts = 4, backoff = @Backoff(delay = 100L, multiplier = 2, maxDelay = 1000))
    public void reportCurrentTime() {
        String date = dateFormat.format(new Date());
        System.out.println("The time is now " + date);

        Wallet response = walletServiceClient.createWallet(new WalletId("test", date));

        System.out.println(response.getWalletId());
    }
}
