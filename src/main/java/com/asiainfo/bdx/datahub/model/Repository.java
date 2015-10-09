package com.asiainfo.bdx.datahub.model;

/**
 * RepositoryId entity. @author MyEclipse Persistence Tools
 */

public class Repository implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long repositoryId;
	private String repositoryName;
	private Long userId;
	private Integer permitType;

	// Constructors

	/** default constructor */
	public Repository() {
	}

	/** minimal constructor */
	public Repository(Long repositoryId) {
		this.repositoryId = repositoryId;
	}

	/** full constructor */
	public Repository(Long repositoryId, String repositoryName, Long userId,
			Integer permitType) {
		this.repositoryId = repositoryId;
		this.repositoryName = repositoryName;
		this.userId = userId;
		this.permitType = permitType;
	}

	// Property accessors

	public Long getRepositoryId() {
		return this.repositoryId;
	}

	public void setRepositoryId(Long repositoryId) {
		this.repositoryId = repositoryId;
	}

	public String getRepositoryName() {
		return this.repositoryName;
	}

	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getPermitType() {
		return this.permitType;
	}

	public void setPermitType(Integer permitType) {
		this.permitType = permitType;
	}

}