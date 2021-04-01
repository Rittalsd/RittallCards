package sd.rittal.cardsmodule.beans;

import com.google.gson.annotations.SerializedName;

public class PinPublicKeyResponse {

    @SerializedName("tranDateTime")
    private String tranDateTime;

    @SerializedName("checkOutID")
    private Object checkOutID;

    @SerializedName("responseMessage")
    private String responseMessage;

    @SerializedName("responseStatus")
    private String responseStatus;

    @SerializedName("pubKeyValue")
    private String pubKeyValue;

    @SerializedName("responseCode")
    private String responseCode;

    public String getTranDateTime() {
        return tranDateTime;
    }

    public void setTranDateTime(String tranDateTime) {
        this.tranDateTime = tranDateTime;
    }

    public Object getCheckOutID() {
        return checkOutID;
    }

    public void setCheckOutID(Object checkOutID) {
        this.checkOutID = checkOutID;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getPubKeyValue() {
        return pubKeyValue;
    }

    public void setPubKeyValue(String pubKeyValue) {
        this.pubKeyValue = pubKeyValue;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
}