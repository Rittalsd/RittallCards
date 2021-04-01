package sd.rittal.cardsmodule.beans;

import com.google.gson.annotations.SerializedName;

public class PaymentInfoResponse {

    @SerializedName("dateTime")
    private String dateTime;

    @SerializedName("amount")
    private String amount;

    @SerializedName("ResponseCode")
    private String responseCode;

    @SerializedName("appName")
    private String appName;

    @SerializedName("language")
    private String language;

    @SerializedName("ResponseMessage")
    private String responseMessage;

    @SerializedName("custName")
    private String custName;

    @SerializedName("custPhone")
    private String custPhone;

    @SerializedName("refrenceID")
    private String refrenceID;

    @SerializedName("ResponseStatus")
    private String responseStatus;

    @SerializedName("returnedURL")
    private String returnedURL;

    @SerializedName("orgPass")
    private String orgPass;

    @SerializedName("currency")
    private String currency;

    @SerializedName("checkOutID")
    private String checkOutID;

    @SerializedName("PaymentStatus")
    private String paymentStatus;

    @SerializedName("orgUsrName")
    private String orgUsrName;

    public String getDateTime() {
        return dateTime;
    }

    public String getAmount() {
        return amount;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getAppName() {
        return appName;
    }

    public String getLanguage() {
        return language;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getCustName() {
        return custName;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public String getRefrenceID() {
        return refrenceID;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public String getReturnedURL() {
        return returnedURL;
    }

    public String getOrgPass() {
        return orgPass;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCheckOutID() {
        return checkOutID;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getOrgUsrName() {
        return orgUsrName;
    }
}