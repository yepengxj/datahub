package com.asiainfo.bdx.datahub.model;

/**
 * FieldId entity. @author MyEclipse Persistence Tools
 */

public class Field implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long dataitemId;
	private Long fieldId;
	private String fieldRawname;
	private String fieldName;
	private Integer primaryKey;
	private Integer fieldDatatype;
	private Integer fieldDatalength;

	// Constructors

	/** default constructor */
	public Field() {
	}

	/** minimal constructor */
	public Field(Long dataitemId, Long fieldId) {
		this.dataitemId = dataitemId;
		this.fieldId = fieldId;
	}

	/** full constructor */
	public Field(Long dataitemId, Long fieldId, String fieldRawname,
			String fieldName, Integer primaryKey, Integer fieldDatatype,
			Integer fieldDatalength) {
		this.dataitemId = dataitemId;
		this.fieldId = fieldId;
		this.fieldRawname = fieldRawname;
		this.fieldName = fieldName;
		this.primaryKey = primaryKey;
		this.fieldDatatype = fieldDatatype;
		this.fieldDatalength = fieldDatalength;
	}

	// Property accessors

	public Long getDataitemId() {
		return this.dataitemId;
	}

	public void setDataitemId(Long dataitemId) {
		this.dataitemId = dataitemId;
	}

	public Long getFieldId() {
		return this.fieldId;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldRawname() {
		return this.fieldRawname;
	}

	public void setFieldRawname(String fieldRawname) {
		this.fieldRawname = fieldRawname;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Integer getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(Integer primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Integer getFieldDatatype() {
		return this.fieldDatatype;
	}

	public void setFieldDatatype(Integer fieldDatatype) {
		this.fieldDatatype = fieldDatatype;
	}

	public Integer getFieldDatalength() {
		return this.fieldDatalength;
	}

	public void setFieldDatalength(Integer fieldDatalength) {
		this.fieldDatalength = fieldDatalength;
	}

}