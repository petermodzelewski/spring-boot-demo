package pl.keyer.spring.swagger.service;

import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.keyer.spring.swagger.service.model.*;

import java.util.List;


@RestController
@RequestMapping(value = "/wallet", produces = "application/json")
public class WalletServiceRestController implements WalletService {
    private final WalletService implementation;

    public WalletServiceRestController() {
        this.implementation = new WalletServiceImpl();
    }

    @Override
    @ApiOperation("Creating new wallet")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created successfully"),
            @ApiResponse(code = 409, message = "Already exists"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public Wallet createWallet(
            @RequestBody @ApiParam("Data necessary for creating a wallet") WalletId walletId){

        return implementation.createWallet(walletId);
    }

    @Override
    @ApiOperation("Getting wallet by given id")
    @RequestMapping(value = "/{walletId}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Operation successful"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public Wallet getWallet(
            @PathVariable @ApiParam("Id of the wallet") String walletId){

        return implementation.getWallet(walletId);
    }

    @Override
    @ApiOperation("Modifying active funds of the wallet")
    @RequestMapping(value = "/{walletId}/modify", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Operation successful"),
            @ApiResponse(code = 409, message = "Operation cannot be completed because of arguments"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public Wallet modifyActiveFundsOnWallet(
            @PathVariable @ApiParam("Id of the wallet") String walletId,
            @RequestBody @ApiParam("Operation details") WalletModification walletModification){

        return implementation.modifyActiveFundsOnWallet(walletId, walletModification);
    }

    @Override
    @ApiOperation("Create transfer between wallets")
    @RequestMapping(value = "/{walletId}/transfer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created successfully"),
            @ApiResponse(code = 409, message = "Operation cannot be completed because of arguments"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public Transfer createTransfer(
            @PathVariable @ApiParam("Id of the source wallet of the transfer") String walletId,
            @RequestBody @ApiParam("Transfer details") TransferArguments transferArguments) {

        return implementation.createTransfer(walletId, transferArguments);
    }

    @Override
    @ApiOperation("Cancel a transfer between wallets")
    @RequestMapping(value = "/{walletId}/transfer/{transferId}/cancel", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Canceled successfully"),
            @ApiResponse(code = 409, message = "Operation cannot be completed because of arguments"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public Transfer cancelTransfer(
            @PathVariable @ApiParam("Id of the source wallet of the transfer") String walletId,
            @PathVariable @ApiParam("Id of the transfer") String transferId) {

        return implementation.cancelTransfer(walletId, transferId);
    }

    @Override
    @ApiOperation("Finalize a transfer between wallets")
    @RequestMapping(value = "/{walletId}/transfer/{transferId}/finalize", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Finalized successfully"),
            @ApiResponse(code = 409, message = "Operation cannot be completed because of arguments"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public Transfer finalizeTransfer(
            @PathVariable @ApiParam("Id of the source wallet of the transfer") String walletId,
            @PathVariable @ApiParam("Id of the transfer") String transferId) {

        return implementation.finalizeTransfer(walletId, transferId);
    }

    @Override
    @ApiOperation("Search for wallet transfers")
    @RequestMapping(value = "/{walletId}/transfer/search", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Operation successful"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public List<Transfer> getTransfers(@PathVariable @ApiParam("Id of the source wallet for the transactions") String walletId) {

        return implementation.getTransfers(walletId);
    }


}
