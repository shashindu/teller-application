package com.ncs.tellerapplication.model;

import java.math.BigDecimal;

import com.sun.xml.txw2.annotation.XmlElement;


public class Transaction{

	
	private String txType;  // CREDIT | DEBIT
	
	private String status;  // COMPLETED | PENDING_APPROVAL
	
	private BigDecimal amount;
	
	private String description;


	public String getTxType() {
		return txType;
	}

	public void setTxType(String txType) {
		this.txType = txType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
