package com.asiainfo.bdx.datahub.model;

/**
 * UserlevelId entity. @author MyEclipse Persistence Tools
 */

public class Userlevel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userLevel;
	private String levelName;

	// Constructors

	/** default constructor */
	public Userlevel() {
	}

	/** minimal constructor */
	public Userlevel(Long userLevel) {
		this.userLevel = userLevel;
	}

	/** full constructor */
	public Userlevel(Long userLevel, String levelName) {
		this.userLevel = userLevel;
		this.levelName = levelName;
	}

	// Property accessors

	public Long getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(Long userLevel) {
		this.userLevel = userLevel;
	}

	public String getLevelName() {
		return this.levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}


}