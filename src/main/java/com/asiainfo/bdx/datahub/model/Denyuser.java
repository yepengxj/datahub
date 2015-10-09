package com.asiainfo.bdx.datahub.model;

/**
 * DenyuserId entity. @author MyEclipse Persistence Tools
 */

public class Denyuser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long dataitemId;
	private Long userId;

	// Constructors

	/** default constructor */
	public Denyuser() {
	}

	/** full constructor */
	public Denyuser(Long dataitemId, Long userId) {
		this.dataitemId = dataitemId;
		this.userId = userId;
	}

	// Property accessors

	public Long getDataitemId() {
		return this.dataitemId;
	}

	public void setDataitemId(Long dataitemId) {
		this.dataitemId = dataitemId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}