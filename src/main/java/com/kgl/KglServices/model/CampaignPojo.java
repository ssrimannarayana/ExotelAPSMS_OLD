package com.kgl.KglServices.model;

public class CampaignPojo {
	public String ID;
	public String ACCOUNT_NUMBER;
	public String MOBILE_NUMBER;
	public String DUE_DATE;
	public String STATE;
	public String STATUS;
	public long DUE_AMOUNT;
	public String CAM_DATE;
	public boolean IVR;
	public boolean SMS;
	public long CAMPAIGN;
	public String CAMPAIGN_SHEET;
	public String NAME;
	public String CAMPAIGN_SID;
	
	public String getCAMPAIGN_SID() {
		return CAMPAIGN_SID;
	}
	public void setCAMPAIGN_SID(String cAMPAIGN_SID) {
		CAMPAIGN_SID = cAMPAIGN_SID;
	}
	public String getID() {
		return ID;
	}
	public String getACCOUNT_NUMBER() {
		return ACCOUNT_NUMBER;
	}
	public String getMOBILE_NUMBER() {
		return MOBILE_NUMBER;
	}
	public String getDUE_DATE() {
		return DUE_DATE;
	}
	public String getSTATE() {
		return STATE;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public long getDUE_AMOUNT() {
		return DUE_AMOUNT;
	}
	public String getCAM_DATE() {
		return CAM_DATE;
	}
	public boolean isIVR() {
		return IVR;
	}
	public boolean isSMS() {
		return SMS;
	}
	public long getCAMPAIGN() {
		return CAMPAIGN;
	}
	public String getCAMPAIGN_SHEET() {
		return CAMPAIGN_SHEET;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public void setACCOUNT_NUMBER(String aCCOUNT_NUMBER) {
		ACCOUNT_NUMBER = aCCOUNT_NUMBER;
	}
	public void setMOBILE_NUMBER(String mOBILE_NUMBER) {
		MOBILE_NUMBER = mOBILE_NUMBER;
	}
	public void setDUE_DATE(String dUE_DATE) {
		DUE_DATE = dUE_DATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public void setDUE_AMOUNT(long dUE_AMOUNT) {
		DUE_AMOUNT = dUE_AMOUNT;
	}
	public void setCAM_DATE(String cAM_DATE) {
		CAM_DATE = cAM_DATE;
	}
	public void setIVR(boolean iVR) {
		IVR = iVR;
	}
	public void setSMS(boolean sMS) {
		SMS = sMS;
	}
	public void setCAMPAIGN(long cAMPAIGN) {
		CAMPAIGN = cAMPAIGN;
	}
	public void setCAMPAIGN_SHEET(String cAMPAIGN_SHEET) {
		CAMPAIGN_SHEET = cAMPAIGN_SHEET;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	
	public CampaignPojo(String iD, String aCCOUNT_NUMBER, String mOBILE_NUMBER, String dUE_DATE, String sTATE,
			String sTATUS, long dUE_AMOUNT, String cAM_DATE, boolean iVR, boolean sMS, long cAMPAIGN,
			String cAMPAIGN_SHEET, String nAME, String cAMPAIGN_SID) {
		super();
		ID = iD;
		ACCOUNT_NUMBER = aCCOUNT_NUMBER;
		MOBILE_NUMBER = mOBILE_NUMBER;
		DUE_DATE = dUE_DATE;
		STATE = sTATE;
		STATUS = sTATUS;
		DUE_AMOUNT = dUE_AMOUNT;
		CAM_DATE = cAM_DATE;
		IVR = iVR;
		SMS = sMS;
		CAMPAIGN = cAMPAIGN;
		CAMPAIGN_SHEET = cAMPAIGN_SHEET;
		NAME = nAME;
		CAMPAIGN_SID = cAMPAIGN_SID;
	}
	public CampaignPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CampaignPojo [ID=" + ID + ", ACCOUNT_NUMBER=" + ACCOUNT_NUMBER + ", MOBILE_NUMBER=" + MOBILE_NUMBER
				+ ", DUE_DATE=" + DUE_DATE + ", STATE=" + STATE + ", STATUS=" + STATUS + ", DUE_AMOUNT=" + DUE_AMOUNT
				+ ", CAM_DATE=" + CAM_DATE + ", IVR=" + IVR + ", SMS=" + SMS + ", CAMPAIGN=" + CAMPAIGN
				+ ", CAMPAIGN_SHEET=" + CAMPAIGN_SHEET + ", NAME=" + NAME + "]";
	}

}
