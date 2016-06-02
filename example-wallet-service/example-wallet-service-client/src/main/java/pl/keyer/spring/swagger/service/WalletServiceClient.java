package pl.keyer.spring.swagger.service;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("WALLET-SERVICE")
public interface WalletServiceClient extends WalletService {
}
