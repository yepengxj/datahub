package com.asiainfo.bdx.datahub.model;

/**
 * DenygroupId entity. @author MyEclipse Persistence Tools
 */

public class Denygroup implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	
	private Long groupId;
	private Long dataitemId;
	

	// Constructors

	/** default constructor */
	public Denygroup() {
	}

	/** full constructor */
	public Denygroup(Long groupId, Long dataitemId) {
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