package pl.keyer.spring.daemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.keyer.spring.swagger.service.WalletServiceClient;

@SpringBootApplication
@EnableScheduling
@EnableRetry
@EnableFeignClients(clients = WalletServiceClient.class)
public class DaemonApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DaemonApplication.class);
    }

}
