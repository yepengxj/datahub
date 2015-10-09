package com.asiainfo.bdx.datahub.model;

/**
 * PermitgroupId entity. @author MyEclipse Persistence Tools
 */

public class Permitgroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long groupId;
	private Long dataitemId;

	// Constructors

	/** default constructor */
	public Permitgroup() {
	}

	/** full constructor */
	public Permitgroup(Long groupId, Long dataitemId) {
		this.groupId = groupId;
		this.dataitemId = dataitemId;
	}

	// Property accessors

	public Long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getDataitemId() {
		return this.dataitemId;
	}

	public void setDataitemId(Long dataitemId) {
		this.dataitemId = dataitemId;
	}

}