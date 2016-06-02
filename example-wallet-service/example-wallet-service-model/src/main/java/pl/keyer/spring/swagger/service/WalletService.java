package pl.keyer.spring.swagger.service;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.keyer.spring.swagger.service.model.*;

import java.util.List;

@RequestMapping(value = "/wallet", produces = "application/json")
public interface WalletService {

    @ApiOperation("Creating new wallet")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created successfully"),
            @ApiResponse(code = 409, message = "Already exists"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    Wallet createWallet(
            @RequestBody @ApiParam("Data necessary for creating a wallet") WalletId walletId);

    @ApiOperation("Getting wallet by given id")
    @RequestMapping(value = "/{walletId}", method = RequestMethod.GET)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Operation successful"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    Wallet getWallet(
            @PathVariable @ApiParam("Id of the wallet") String walletId);


    @ApiOperation("Modifying active funds of the wallet")
    @RequestMapping(value = "/{walletId}/modify", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Operation successful"),
            @ApiResponse(code = 409, message = "Operation cannot be completed because of arguments"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    Wallet modifyActiveFundsOnWallet(
            @PathVariable @ApiParam("Id of the wallet") String walletId,
            @RequestBody @ApiParam("Operation details") WalletModification walletModification);

    @ApiOperation("Create transfer between wallets")
    @RequestMapping(value = "/{walletId}/transfer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created successfully"),
            @ApiResponse(code = 409, message = "Operation cannot be completed because of arguments"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    Transfer createTransfer(
            @PathVariable @ApiParam("Id of the source wallet of the transfer") String walletId,
            @RequestBody @ApiParam("Transfer details") TransferArguments transferArguments);

    @ApiOperation("Cancel a transfer between wallets")
    @RequestMapping(value = "/{walletId}/transfer/{transferId}/cancel", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Canceled successfully"),
            @ApiResponse(code = 409, message = "Operation cannot be completed because of arguments"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    Transfer cancelTransfer(
            @PathVariable @ApiParam("Id of the source wallet of the transfer") String walletId,
            @PathVariable @ApiParam("Id of the transfer") String transferId);

    @ApiOperation("Finalize a transfer between wallets")
    @RequestMapping(value = "/{walletId}/transfer/{transferId}/finalize", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Finalized successfully"),
            @ApiResponse(code = 409, message = "Operation cannot be completed because of arguments"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    Transfer finalizeTransfer(
            @PathVariable @ApiParam("Id of the source wallet of the transfer") String walletId,
            @PathVariable @ApiParam("Id of the transfer") String transferId);


    @ApiOperation("Search for wallet transfers")
    @RequestMapping(value = "/{walletId}/transfer/search", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Operation successful"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    List<Transfer> getTransfers(@PathVariable @ApiParam("Id of the source wallet for the transactions") String walletId);
}
