package com.asiainfo.bdx.datahub.model;

/**
 * UserstatusId entity. @author MyEclipse Persistence Tools
 */

public class Userstatus implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userStatus;
	private String statusName;

	// Constructors

	/** default constructor */
	public Userstatus() {
	}

	/** minimal constructor */
	public Userstatus(Long userStatus) {
		this.userStatus = userStatus;
	}

	/** full constructor */
	public Userstatus(Long userStatus, String statusName) {
		this.userStatus = userStatus;
		this.statusName = statusName;
	}

	// Property accessors

	public Long getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(Long userStatus) {
		this.userStatus = userStatus;
	}

	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}


}