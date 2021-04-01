package sd.rittal.cardsmodule.beans;

import com.google.gson.annotations.SerializedName;

public class PaymentInfoRequest {

    @SerializedName("orgUsrName")
    private String orgUsrName;

    @SerializedName("appName")
    private String appName;

    @SerializedName("orgPass")
    private String orgPass;

    @SerializedName("CheckOutID")
    private String checkOutID;

    public PaymentInfoRequest(String orgUsrName, String appName, String orgPass, String checkOutID) {
        this.orgUsrName = orgUsrName;
        this.appName = appName;
        this.orgPass = orgPass;
        this.checkOutID = checkOutID;
    }
}
