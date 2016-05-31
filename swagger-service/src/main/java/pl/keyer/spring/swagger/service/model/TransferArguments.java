package pl.keyer.spring.swagger.service.model;

import io.swagger.annotations.ApiModelProperty;

public class TransferArguments {
    @ApiModelProperty(notes = "Unique id of the wallet the founds are going int", example = "USER_124", required = true)
    private String targetWalletId;

    @ApiModelProperty(notes = "Amount of founds that is being transferred", example = "20", required = true)
    private int ammount;

    @ApiModelProperty(notes = "Reason for the transfer", example = "User donating to user", required = true)
    private String reason;

    TransferArguments() {} //we need empty constructor

    public TransferArguments(String targetWalletId, int ammount, String reason) {
        this.targetWalletId = targetWalletId;
        this.ammount = ammount;
        this.reason = reason;
    }

    public String getTargetWalletId() {
        return targetWalletId;
    }

    public int getAmmount() {
        return ammount;
    }

    public String getReason() {
        return reason;
    }
}
