package com.asiainfo.bdx.datahub.model;

/**
 * SupplystyleId entity. @author MyEclipse Persistence Tools
 */

public class Supplystyle implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long supplyStyle;
	private String supplyName;

	// Constructors

	/** default constructor */
	public Supplystyle() {
	}

	/** full constructor */
	public Supplystyle(Long supplyStyle, String supplyName) {
		this.supplyStyle = supplyStyle;
		this.supplyName = supplyName;
	}

	// Property accessors

	public Long getSupplyStyle() {
		return this.supplyStyle;
	}

	public void setSupplyStyle(Long supplyStyle) {
		this.supplyStyle = supplyStyle;
	}

	public String getSupplyName() {
		return this.supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}


}