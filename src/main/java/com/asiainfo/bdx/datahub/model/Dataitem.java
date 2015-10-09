package com.asiainfo.bdx.datahub.model;

import java.util.Date;

/**
 * DataitemId entity. @author MyEclipse Persistence Tools
 */

public class Dataitem implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long repositoryId;
	private Long userId;
	private Long dataitemId;
	private String dataitemName;
	private String icoName;
	private String tag;
	private Integer permitType;
	private Integer supplyStyle;
	private Long priceunitType;
	private Double price;
	private Date optime;
	private String fileType;
	private Integer refreshType;
	private Date refreshDate;
	private String sampleFilename;
	private String comment;
	private String tradeType;
	private String strRefreshtype;
	private String dataDate;

	public String getDataDate() {
		return dataDate;
	}

	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}

	public String getStrRefreshtype() {
		return strRefreshtype;
	}

	public void setStrRefreshtype(String strRefreshtype) {
		this.strRefreshtype = strRefreshtype;
	}
// Constructors

	/** default constructor */
	public Dataitem() {
	}

	/** minimal constructor */
	public Dataitem(Long repositoryId, Long dataitemId) {
		this.repositoryId = repositoryId;
		this.dataitemId = dataitemId;
	}

	/** full constructor */
	public Dataitem(Long repositoryId, Long userId, Long dataitemId,
			String dataitemName,String icoName, String tag, Integer permitType,
			Integer supplyStyle, Long priceunitType, Double price, Date optime,String fileType,
			Integer refreshType,Date refreshDate, String sampleFilename, String comment) {
		this.repositoryId = repositoryId;
		this.userId = userId;
		this.dataitemId = dataitemId;
		this.dataitemName = dataitemName;
		this.icoName = icoName;
		this.tag = tag;
		this.permitType = permitType;
		this.supplyStyle = supplyStyle;
		this.priceunitType = priceunitType;
		this.price = price;
		this.optime = optime;
		this.fileType = fileType;
		this.refreshType = refreshType;
		this.refreshDate = refreshDate;
		this.sampleFilename = sampleFilename;
		this.comment = comment;
	}

	// Property accessors

	public Long getRepositoryId() {
		return this.repositoryId;
	}

	public void setRepositoryId(Long repositoryId) {
		this.repositoryId = repositoryId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDataitemId() {
		return this.dataitemId;
	}

	public void setDataitemId(Long dataitemId) {
		this.dataitemId = dataitemId;
	}

	public String getDataitemName() {
		return this.dataitemName;
	}

	public void setDataitemName(String dataitemName) {
		this.dataitemName = dataitemName;
	}

	/**
	 * @return the icoName
	 */
	public String getIcoName() {
		return icoName;
	}

	/**
	 * @param icoName the icoName to set
	 */
	public void setIcoName(String icoName) {
		this.icoName = icoName;
	}
	
	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getPermitType() {
		return this.permitType;
	}

	public void setPermitType(Integer permitType) {
		this.permitType = permitType;
	}

	public Integer getSupplyStyle() {
		return this.supplyStyle;
	}

	public void setSupplyStyle(Integer supplyStyle) {
		this.supplyStyle = supplyStyle;
	}

	public Long getPriceunitType() {
		return this.priceunitType;
	}

	public void setPriceunitType(Long priceunitType) {
		this.priceunitType = priceunitType;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getOptime() {
		return this.optime;
	}

	public void setOptime(Date optime) {
		this.optime = optime;
	}
	
	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Integer getRefreshType() {
		return this.refreshType;
	}

	public void setRefreshType(Integer refreshType) {
		this.refreshType = refreshType;
	}

	/**
	 * @return the refreshDate
	 */
	public Date getRefreshDate() {
		return refreshDate;
	}

	/**
	 * @param refreshDate the refreshDate to set
	 */
	public void setRefreshDate(Date refreshDate) {
		this.refreshDate = refreshDate;
	}

	public String getSampleFilename() {
		return this.sampleFilename;
	}

	public void setSampleFilename(String sampleFilename) {
		this.sampleFilename = sampleFilename;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
}