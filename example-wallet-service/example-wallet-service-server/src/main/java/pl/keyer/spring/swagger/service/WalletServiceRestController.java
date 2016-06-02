package pl.keyer.spring.swagger.service;

import org.springframework.web.bind.annotation.RestController;
import pl.keyer.spring.swagger.service.model.*;
import com.google.common.collect.Lists;

import java.util.List;

@RestController
public class WalletServiceRestController implements WalletService {

    @Override
    public Wallet createWallet(WalletId walletId) {
        return getWallet(walletId.getType() + "_" + walletId.getId());
    }

    @Override
    public Wallet getWallet(String walletId) {
        return new Wallet(walletId, 0, 0, "todo", "todo");
    }

    @Override
    public Wallet modifyActiveFundsOnWallet(String walletId, WalletModification walletModification) {
        return new Wallet(walletId, walletModification.getAmount(), 0, "todo", "todo");
    }

    @Override
    public Transfer createTransfer(String walletId, TransferArguments transferArguments) {
        return new Transfer("transfer_id", walletId, transferArguments);
    }

    @Override
    public Transfer cancelTransfer(String walletId, String transferId) {
        return new Transfer("123", "u_1", "ch_2", 123, "Reason", TransferStatus.CANCELED);
    }

    @Override
    public Transfer finalizeTransfer(String walletId, String transferId) {
        return new Transfer("123", "u_1", "ch_2", 123, "Reason", TransferStatus.FINISHED);
    }

    @Override
    public List<Transfer> getTransfers(String walletId) {
        return Lists.newArrayList(
                new Transfer("123", "u_1", "ch_2", 123, "Reason", TransferStatus.CANCELED),
                new Transfer("123", "u_1", "ch_3", 123, "Reason", TransferStatus.FINISHED)
        );
    }


}
