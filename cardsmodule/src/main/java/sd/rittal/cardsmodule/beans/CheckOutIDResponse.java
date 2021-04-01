package sd.rittal.cardsmodule.beans;

import com.google.gson.annotations.SerializedName;

public class CheckOutIDResponse {

    @SerializedName("checkOutID")
    private String checkOutID;

    @SerializedName("refrenceID")
    private String refrenceID;

    @SerializedName("custName")
    private String custName;

    @SerializedName("custPhone")
    private String custPhone;

    @SerializedName("dateTime")
    private String dateTime;

    @SerializedName("language")
    private String language;

    @SerializedName("returnedURL")
    private String returnedURL;

    @SerializedName("currency")
    private String currency;

    @SerializedName("amount")
    private String amount;


    // below are fixed
    @SerializedName("ResponseStatus")
    private String responseStatus;

    @SerializedName("ResponseCode")
    private String responseCode;

    @SerializedName("ResponseMessage")
    private String responseMessage;


    public String getCheckOutID() {
        return checkOutID;
    }

    public String getRefrenceID() {
        return refrenceID;
    }

    public String getCustName() {
        return custName;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getLanguage() {
        return language;
    }

    public String getReturnedURL() {
        return returnedURL;
    }

    public String getCurrency() {
        return currency;
    }

    public String getAmount() {
        return amount;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
