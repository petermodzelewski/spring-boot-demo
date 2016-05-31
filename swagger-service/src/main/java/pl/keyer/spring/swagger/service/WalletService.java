package pl.keyer.spring.swagger.service;

import pl.keyer.spring.swagger.service.model.*;

import java.util.List;

public interface WalletService {
    Wallet createWallet(WalletId walletId);
    Wallet getWallet(String walletId);
    Wallet modifyActiveFundsOnWallet(String walletId, WalletModification walletModification);
    Transfer createTransfer(String walletId, TransferArguments transferArguments);
    Transfer cancelTransfer(String walletId, String transferId);
    Transfer finalizeTransfer(String walletId, String transferId);
    List<Transfer> getTransfers(String walletId);
}
