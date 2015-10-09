package com.asiainfo.bdx.datahub.model;

/**
 * UserlistId entity. @author MyEclipse Persistence Tools
 */

public class Userlist implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long groupId;
	private Long userId;

	// Constructors

	/** default constructor */
	public Userlist() {
	}

	/** full constructor */
	public Userlist(Long groupId, Long userId) {
		this.groupId = groupId;
		this.userId = userId;
	}

	// Property accessors

	public Long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}