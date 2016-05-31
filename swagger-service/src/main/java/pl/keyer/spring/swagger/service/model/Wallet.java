package pl.keyer.spring.swagger.service.model;

import io.swagger.annotations.ApiModelProperty;

public class Wallet {
    private String walletId;
    private int activeFunds;
    private int frozenFunds;
    private String type;
    private String idOfType;

    // we need empty constructor
    Wallet(){
    }

    public Wallet(String walletId, int activeFunds, int frozenFunds, String type, String idOfType) {
        this.walletId = walletId;
        this.activeFunds = activeFunds;
        this.frozenFunds = frozenFunds;
        this.type = type;
        this.idOfType = idOfType;
    }

    @ApiModelProperty(notes = "Unique id of the wallet", example = "USER_123", required = true)
    public String getWalletId() {
        return walletId;
    }

    @ApiModelProperty(notes = "Number of funds that are for its owner to operate with", example = "1000", required = true)
    public int getActiveFunds() {
        return activeFunds;
    }

    @ApiModelProperty(notes = "Number of funds that frozen because of pending transactions", example = "100", required = true)
    public int getFrozenFunds() {
        return frozenFunds;
    }

    @ApiModelProperty(notes = "The type of the wallet, for example USER, or CHALLENGE", example = "USER", required = true)
    public String getType() {
        return type;
    }

    @ApiModelProperty(notes = "The unique id for given type", example = "123", required = true)
    public String getIdOfType() {
        return idOfType;
    }
}
