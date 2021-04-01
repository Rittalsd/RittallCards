package sd.rittal.cardsmodule.beans;

import com.google.gson.annotations.SerializedName;

public class PinPublicKeyRequest {

    @SerializedName("orgUsrName")
    private String orgUsrName;

    @SerializedName("appName")
    private String appName;

    @SerializedName("orgPass")
    private String orgPass;

    public PinPublicKeyRequest(String orgUsrName, String appName, String orgPass) {
        this.orgUsrName = orgUsrName;
        this.appName = appName;
        this.orgPass = orgPass;
    }
}