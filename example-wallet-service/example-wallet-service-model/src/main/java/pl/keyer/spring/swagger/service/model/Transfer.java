package pl.keyer.spring.swagger.service.model;

import io.swagger.annotations.ApiModelProperty;

public class Transfer {
    private String id;
    private String sourceWalletId;
    private String targetWalletId;
    private int amount;
    private String reason;
    private TransferStatus status;

    public Transfer() {} //

    public Transfer(String id, String sourceWalletId, String targetWalletId, int amount, String reason, TransferStatus status) {
        this.id = id;
        this.sourceWalletId = sourceWalletId;
        this.targetWalletId = targetWalletId;
        this.amount = amount;
        this.reason = reason;
        this.status = status;
    }

    public Transfer(String id, String sourceWalletId, TransferArguments transferArguments) {
        this(id, sourceWalletId, transferArguments.getTargetWalletId(), transferArguments.getAmmount(), transferArguments.getReason(), TransferStatus.STARTED);
    }

    @ApiModelProperty(notes = "Unique id the transfer", example = "ABC123", required = true)
    public String getId() {
        return id;
    }

    @ApiModelProperty(notes = "Id of the wallet from the founds are going out", example = "USER_123", required = true)
    public String getSourceWalletId() {
        return sourceWalletId;
    }

    @ApiModelProperty(notes = "Id of the wallet the founds are going into", example = "USER_124", required = true)
    public String getTargetWalletId() {
        return targetWalletId;
    }

    @ApiModelProperty(notes = "Amount of founds that is being transfered", example = "20", required = true)
    public int getAmount() {
        return amount;
    }

    @ApiModelProperty(notes = "Reason for the transfer", example = "User donating to user", required = true)
    public String getReason() {
        return reason;
    }

    @ApiModelProperty(notes = "Transfer status", example = "STARTED", required = true)
    public TransferStatus getStatus() {
        return status;
    }
}
