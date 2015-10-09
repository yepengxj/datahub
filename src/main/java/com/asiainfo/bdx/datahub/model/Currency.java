package com.asiainfo.bdx.datahub.model;

/**
 * Currency entity. @author MyEclipse Persistence Tools
 */

public class Currency implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long currencyType;
	private String currencyName;
	private String comment;

	// Constructors

	/** default constructor */
	public Currency() {
	}

	public Long getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(Long currencyType) {
		this.currencyType = currencyType;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


}