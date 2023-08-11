package com.kgl.KglServices.model;

public class PhonepeResponseObj {

	private String id, campaignSheet, code, message, merchantId, transactionId, providerReferenceId,
			paymentState, payResponseCode, mode, ifsc, utr, upiTransactionId;
	private boolean success;

	public String getId() {
		return id;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getCampaignSheet() {
		return campaignSheet;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public String getProviderReferenceId() {
		return providerReferenceId;
	}

	public String getPaymentState() {
		return paymentState;
	}

	public String getPayResponseCode() {
		return payResponseCode;
	}

	public String getMode() {
		return mode;
	}

	public String getIfsc() {
		return ifsc;
	}

	public String getUtr() {
		return utr;
	}

	public String getUpiTransactionId() {
		return upiTransactionId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCampaignSheet(String campaignSheet) {
		this.campaignSheet = campaignSheet;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public void setProviderReferenceId(String providerReferenceId) {
		this.providerReferenceId = providerReferenceId;
	}

	public void setPaymentState(String paymentState) {
		this.paymentState = paymentState;
	}

	public void setPayResponseCode(String payResponseCode) {
		this.payResponseCode = payResponseCode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public void setUtr(String utr) {
		this.utr = utr;
	}

	public void setUpiTransactionId(String upiTransactionId) {
		this.upiTransactionId = upiTransactionId;
	}
	
}
