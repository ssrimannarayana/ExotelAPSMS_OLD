package com.kgl.KglServices.model;

import java.io.Serializable;

public class ScriptObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String sno;
	public String empid;
	public String loanacno;
	public String name;
	public String phone;
	public String loanamount;
	public String paylink;
	public String duedate;
	public String status;
	public String state;
	public String sms;
	public long campaign;
	public String campdate;
	public boolean ivrStatus;
	public boolean smsStatus;
	public String campaignSheet;
	
	public String getCampaignSheet() {
		return campaignSheet;
	}

	public void setCampaignSheet(String campaignSheet) {
		this.campaignSheet = campaignSheet;
	}

	public String getSno() {
		return sno;
	}

	public String getEmpid() {
		return empid;
	}

	public String getPhone() {
		return phone;
	}

	public String getLoanacno() {
		return loanacno;
	}

	public String getName() {
		return name;
	}

	public String getLoanamount() {
		return loanamount;
	}

	public String getDuedate() {
		return duedate;
	}

	public String getState() {
		return state;
	}

	public String getPaylink() {
		return paylink;
	}

	public String getStatus() {
		return status;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setLoanacno(String loanacno) {
		this.loanacno = loanacno;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLoanamount(String loanamount) {
		this.loanamount = loanamount;
	}

	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setPaylink(String paylink) {
		this.paylink = paylink;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public long getCampaign() {
		return campaign;
	}

	public void setCampaign(long campaign) {
		this.campaign = campaign;
	}

	public String getCampdate() {
		return campdate;
	}

	public boolean isIvrStatus() {
		return ivrStatus;
	}

	public boolean isSmsStatus() {
		return smsStatus;
	}

	public void setCampdate(String campdate) {
		this.campdate = campdate;
	}

	public void setIvrStatus(boolean ivrStatus) {
		this.ivrStatus = ivrStatus;
	}

	public void setSmsStatus(boolean smsStatus) {
		this.smsStatus = smsStatus;
	}

}
