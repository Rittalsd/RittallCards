package sd.rittal.cardsmodule.beans;

import com.google.gson.annotations.SerializedName;

public class CheckOutIDRequest {

    @SerializedName("orgUsrName")
    private String orgUsrName;

    @SerializedName("appName")
    private String appName;

    @SerializedName("orgPass")
    private String orgPass;


    @SerializedName("amount")
    private String amount;

    @SerializedName("returnedURL")
    private String returnedURL;

    public CheckOutIDRequest(String orgUsrName, String appName, String orgPass, String amount, String returnedURL) {
        this.orgUsrName = orgUsrName;
        this.appName = appName;
        this.orgPass = orgPass;

        this.amount = amount;
        this.returnedURL = returnedURL;
    }
}
