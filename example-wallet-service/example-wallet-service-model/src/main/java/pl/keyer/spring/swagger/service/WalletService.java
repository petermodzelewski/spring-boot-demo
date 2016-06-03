package pl.keyer.spring.swagger.service;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.keyer.spring.swagger.service.model.Wallet;
import pl.keyer.spring.swagger.service.model.WalletId;
import pl.keyer.spring.swagger.service.model.WalletModification;

@RequestMapping(value = "/wallet", produces = "application/json")
@Api(tags = "WALLET-SERVICE", description = "Basic wallet operations")
public interface WalletService {

    @ApiOperation("Creating new wallet")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created successfully"),
            @ApiResponse(code = 409, message = "Already exists"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(method = RequestMethod.POST)
    Wallet createWallet(
            @RequestBody @ApiParam("Data necessary for creating a wallet") WalletId walletId);

    @ApiOperation("Getting wallet by given id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Operation successful"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/{walletId}", method = RequestMethod.GET)
    Wallet getWallet(@PathVariable("walletId") @ApiParam("Id of the wallet") String walletId);


    @ApiOperation("Modifying active funds of the wallet")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Operation successful"),
            @ApiResponse(code = 409, message = "Operation cannot be completed because of arguments"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @RequestMapping(value = "/{walletId}/modify", method = RequestMethod.POST)
    Wallet modifyActiveFundsOnWallet(
            @PathVariable("walletId") @ApiParam("Id of the wallet")  String walletId,
            @RequestBody @ApiParam("Operation details") WalletModification walletModification);

}
