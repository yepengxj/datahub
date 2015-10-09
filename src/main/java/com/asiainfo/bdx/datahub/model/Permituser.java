package com.asiainfo.bdx.datahub.model;

/**
 * PermituserId entity. @author MyEclipse Persistence Tools
 */

public class Permituser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long dataitemId;
	private Long userId;

	// Constructors

	/** default constructor */
	public Permituser() {
	}

	/** full constructor */
	public Permituser(Long dataitemId, Long userId) {
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