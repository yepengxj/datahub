package com.asiainfo.bdx.datahub.model;

/**
 * PriceunitId entity. @author MyEclipse Persistence Tools
 */

public class Priceunit implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long priceunitTpe;
	private Long currencyType;
	private String priceunitName;

	// Constructors

	/** default constructor */
	public Priceunit() {
	}

	/** minimal constructor */
	public Priceunit(Long priceunitTpe) {
		this.priceunitTpe = priceunitTpe;
	}

	/** full constructor */
	public Priceunit(Long priceunitTpe, Long currencyType,
			String priceunitName) {
		this.priceunitTpe = priceunitTpe;
		this.currencyType = currencyType;
		this.priceunitName = priceunitName;
	}

	// Property accessors

	public Long getPriceunitTpe() {
		return this.priceunitTpe;
	}

	public void setPriceunitTpe(Long priceunitTpe) {
		this.priceunitTpe = priceunitTpe;
	}

	public Long getCurrencyType() {
		return this.currencyType;
	}

	public void setCurrencyType(Long currencyType) {
		this.currencyType = currencyType;
	}

	public String getPriceunitName() {
		return this.priceunitName;
	}

	public void setPriceunitName(String priceunitName) {
		this.priceunitName = priceunitName;
	}

}