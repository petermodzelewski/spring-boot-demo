package pl.keyer.spring.somerestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GreetingService {
    public static void main(String[] args) {
        SpringApplication.run(GreetingService.class, args);
    }
}
