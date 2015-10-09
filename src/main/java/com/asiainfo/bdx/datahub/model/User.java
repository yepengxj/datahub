package com.asiainfo.bdx.datahub.model;

/**
 * UserId entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Integer userLevel;
	private Long companyId;
	private String loginName;
	private String loginPasswd;
	private String phoneNo;
	private String email;
	private Long cloneUser;
	private Integer userStatus;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Long userId) {
		this.userId = userId;
	}

	/** full constructor */
	public User(Long userId, Integer userLevel, Long companyId,
			String loginName, String loginPasswd, String phoneNo, String email,
			Long cloneUser, Integer userStatus) {
		this.userId = userId;
		this.userLevel = userLevel;
		this.companyId = companyId;
		this.loginName = loginName;
		this.loginPasswd = loginPasswd;
		this.phoneNo = phoneNo;
		this.email = email;
		this.cloneUser = cloneUser;
		this.userStatus = userStatus;
	}

	// Property accessors

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPasswd() {
		return this.loginPasswd;
	}

	public void setLoginPasswd(String loginPasswd) {
		this.loginPasswd = loginPasswd;
	}

	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getCloneUser() {
		return this.cloneUser;
	}

	public void setCloneUser(Long cloneUser) {
		this.cloneUser = cloneUser;
	}

	public Integer getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}


}