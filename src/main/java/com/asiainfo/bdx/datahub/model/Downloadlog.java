package com.asiainfo.bdx.datahub.model;

import java.sql.Timestamp;

/**
 * DownloadlogId entity. @author MyEclipse Persistence Tools
 */

public class Downloadlog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long dataitemId;
	private Timestamp dataDate;
	private Integer downUser;
	private Timestamp opTime;

	// Constructors

	/** default constructor */
	public Downloadlog() {
	}

	/** full constructor */
	public Downloadlog(Long dataitemId, Timestamp dataDate, Integer downUser,
			Timestamp opTime) {
		this.dataitemId = dataitemId;
		this.dataDate = dataDate;
		this.downUser = downUser;
		this.opTime = opTime;
	}

	// Property accessors

	public Long getDataitemId() {
		return this.dataitemId;
	}

	public void setDataitemId(Long dataitemId) {
		this.dataitemId = dataitemId;
	}

	public Timestamp getDataDate() {
		return this.dataDate;
	}

	public void setDataDate(Timestamp dataDate) {
		this.dataDate = dataDate;
	}

	public Integer getDownUser() {
		return this.downUser;
	}

	public void setDownUser(Integer downUser) {
		this.downUser = downUser;
	}

	public Timestamp getOpTime() {
		return this.opTime;
	}

	public void setOpTime(Timestamp opTime) {
		this.opTime = opTime;
	}

}