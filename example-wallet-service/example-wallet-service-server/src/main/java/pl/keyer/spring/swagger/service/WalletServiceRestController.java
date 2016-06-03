package pl.keyer.spring.swagger.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.keyer.spring.swagger.service.model.Wallet;
import pl.keyer.spring.swagger.service.model.WalletId;
import pl.keyer.spring.swagger.service.model.WalletModification;

@RestController
public class WalletServiceRestController implements WalletService {

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public Wallet createWallet(@RequestBody WalletId walletId) {
        return getWallet(walletId.getType() + "_" + walletId.getId());
    }

    @Override
    public Wallet getWallet(@PathVariable("walletId") String walletId) {
        return new Wallet(walletId, 0, 0, "todo", "todo");
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public Wallet modifyActiveFundsOnWallet(@PathVariable("walletId") String walletId, @RequestBody WalletModification walletModification) {
        return new Wallet(walletId, walletModification.getAmount(), 0, "todo", "todo");
    }

}
