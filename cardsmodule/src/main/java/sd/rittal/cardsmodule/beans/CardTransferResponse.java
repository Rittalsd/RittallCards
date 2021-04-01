package sd.rittal.cardsmodule.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CardTransferResponse implements Serializable {

    @SerializedName("acqTranFee")
    private double acqTranFee;

    @SerializedName("dynamicFees")
    private String dynamicFees;

    @SerializedName("toAccount")
    private String toAccount;

    @SerializedName("toCard")
    private String toCard;

    @SerializedName("fromAccount")
    private String fromAccount;

    @SerializedName("accountCurrency")
    private String accountCurrency;

    @SerializedName("expDate")
    private String expDate;

    @SerializedName("responseCode")
    private int responseCode;

    @SerializedName("fromAccountType")
    private String fromAccountType;

    @SerializedName("issuerTranFee")
    private double issuerTranFee;

    @SerializedName("UUID")
    private String uUID;

    @SerializedName("Mbr")
    private String mbr;

    @SerializedName("tranDateTime")
    private String tranDateTime;

    @SerializedName("toAccountType")
    private String toAccountType;

    @SerializedName("tranCurrency")
    private String tranCurrency;

    @SerializedName("entityType")
    private String entityType;

    @SerializedName("checkDuplicate")
    private String checkDuplicate;

    @SerializedName("entityId")
    private String entityId;

    @SerializedName("responseStatus")
    private String responseStatus;

    @SerializedName("Username")
    private String username;

    @SerializedName("authenticationType")
    private String authenticationType;

    @SerializedName("responseMessage")
    private String responseMessage;

    @SerializedName("PAN")
    private String pAN;

    @SerializedName("tranAmount")
    private double tranAmount;

    @SerializedName("Balance")
    private String balance;

    public double getAcqTranFee() {
        return acqTranFee;
    }

    public String getDynamicFees() {
        return dynamicFees;
    }

    public String getToAccount() {
        return toAccount;
    }

    public String getToCard() {
        return toCard;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public String getAccountCurrency() {
        return accountCurrency;
    }

    public String getExpDate() {
        return expDate;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getFromAccountType() {
        return fromAccountType;
    }

    public double getIssuerTranFee() {
        return issuerTranFee;
    }

    public String getUUID() {
        return uUID;
    }

    public String getMbr() {
        return mbr;
    }

    public String getTranDateTime() {
        return tranDateTime;
    }

    public String getToAccountType() {
        return toAccountType;
    }

    public String getTranCurrency() {
        return tranCurrency;
    }

    public String getEntityType() {
        return entityType;
    }

    public String getCheckDuplicate() {
        return checkDuplicate;
    }

    public String getEntityId() {
        return entityId;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public String getUsername() {
        return username;
    }

    public String getAuthenticationType() {
        return authenticationType;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getPAN() {
        return pAN;
    }

    public double getTranAmount() {
        return tranAmount;
    }

    public String getBalance() {
        return balance;
    }
}