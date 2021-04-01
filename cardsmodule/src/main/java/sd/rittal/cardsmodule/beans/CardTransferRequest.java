package sd.rittal.cardsmodule.beans;

import com.google.gson.annotations.SerializedName;

public class CardTransferRequest {


	public CardTransferRequest(String toCard, String appName, String orgPass, String authenticationType,
							   String tranAmount, String pAN, String uUID, String expDate, String iPIN, String orgUsrName) {
		this.toCard = toCard;
		this.appName = appName;
		this.orgPass = orgPass;
		this.authenticationType = authenticationType;
		this.tranAmount = tranAmount;
		this.pAN = pAN;
		this.uUID = uUID;
		this.expDate = expDate;
		this.iPIN = iPIN;
		this.orgUsrName = orgUsrName;
	}

	@SerializedName("toCard")
	private String toCard;

	@SerializedName("appName")
	private String appName;

	@SerializedName("orgPass")
	private String orgPass;

	@SerializedName("authenticationType")
	private String authenticationType;

	@SerializedName("tranAmount")
	private String tranAmount;

	@SerializedName("PAN")
	private String pAN;

	@SerializedName("UUID")
	private String uUID;

	@SerializedName("expDate")
	private String expDate;

	@SerializedName("IPIN")
	private String iPIN;

	@SerializedName("orgUsrName")
	private String orgUsrName;

	public String getToCard(){
		return toCard;
	}

	public String getAppName(){
		return appName;
	}

	public String getOrgPass(){
		return orgPass;
	}

	public String getAuthenticationType(){
		return authenticationType;
	}

	public String getTranAmount(){
		return tranAmount;
	}

	public String getPAN(){
		return pAN;
	}

	public String getUUID(){
		return uUID;
	}

	public String getExpDate(){
		return expDate;
	}

	public String getIPIN(){
		return iPIN;
	}

	public String getOrgUsrName(){
		return orgUsrName;
	}
}