package pl.keyer.spring.swagger.service.model;

import io.swagger.annotations.ApiModelProperty;

public class WalletId {
    @ApiModelProperty(notes = "The type of the wallet, for example USER", required = true, example = "USER")
    private String type;
    @ApiModelProperty(notes = "The unique id for given type", required = true, example = "123")
    private String id;

    //we need empty constructor
    WalletId(){
    }

    public WalletId(String type, String id) {
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}
