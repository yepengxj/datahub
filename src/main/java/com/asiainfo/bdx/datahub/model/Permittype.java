package com.asiainfo.bdx.datahub.model;

/**
 * PermittypeId entity. @author MyEclipse Persistence Tools
 */

public class Permittype implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long permitType;
	private String permitName;

	// Constructors

	/** default constructor */
	public Permittype() {
	}

	/** full constructor */
	public Permittype(Long permitType, String permitName) {
		this.permitType = permitType;
		this.permitName = permitName;
	}

	// Property accessors

	public Long getPermitType() {
		return this.permitType;
	}

	public void setPermitType(Long permitType) {
		this.permitType = permitType;
	}

	public String getPermitName() {
		return this.permitName;
	}

	public void setPermitName(String permitName) {
		this.permitName = permitName;
	}

}