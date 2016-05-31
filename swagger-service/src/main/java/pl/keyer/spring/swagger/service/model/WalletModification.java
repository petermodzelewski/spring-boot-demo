package pl.keyer.spring.swagger.service.model;

import io.swagger.annotations.ApiModelProperty;

public class WalletModification {
    @ApiModelProperty(notes = "Operation value (can be positive and negative)", required = true, example = "100")
    private int amount;
    @ApiModelProperty(notes = "Justification for the operation)", required = true, example = "PayPal payment")
    private String description;

    WalletModification() {} // we need empty constructor

    public WalletModification(int amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}
