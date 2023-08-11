package com.kgl.KglServices.model;

public class PhonepeObj {
	 private String merchantId; 
	 private String transactionId;
	 private String merchantOrderId;
	 private long amount;
	 private String mobileNumber;
	 private String message;
	 private int expiresIn;
	
	 
	public String getMerchantId() {
		return merchantId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public String getMerchantOrderId() {
		return merchantOrderId;
	}
	public long getAmount() {
		return amount;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public String getMessage() {
		return message;
	}
	
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public void setMerchantOrderId(String merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}
