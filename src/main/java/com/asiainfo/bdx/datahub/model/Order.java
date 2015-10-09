package com.asiainfo.bdx.datahub.model;

import java.sql.Timestamp;

/**
 * OrderId entity. @author MyEclipse Persistence Tools
 */

public class Order implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long orderId;
	private Long dataId;
	private Long buyer;
	private Integer amount;
	private Double listPrice;
	private Double applyPrice;
	private Double ackPrice;
	private Integer orderStatus;
	private Timestamp optime;
	private String comment;

	// Constructors

	/** default constructor */
	public Order() {
	}

	/** minimal constructor */
	public Order(Long orderId) {
		this.orderId = orderId;
	}

	/** full constructor */
	public Order(Long orderId, Long dataId, Long buyer, Integer amount,
			Double listPrice, Double applyPrice, Double ackPrice,
			Integer orderStatus, Timestamp optime, String comment) {
		this.orderId = orderId;
		this.dataId = dataId;
		this.buyer = buyer;
		this.amount = amount;
		this.listPrice = listPrice;
		this.applyPrice = applyPrice;
		this.ackPrice = ackPrice;
		this.orderStatus = orderStatus;
		this.optime = optime;
		this.comment = comment;
	}

	// Property accessors

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getDataId() {
		return this.dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public Long getBuyer() {
		return this.buyer;
	}

	public void setBuyer(Long buyer) {
		this.buyer = buyer;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getListPrice() {
		return this.listPrice;
	}

	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}

	public Double getApplyPrice() {
		return this.applyPrice;
	}

	public void setApplyPrice(Double applyPrice) {
		this.applyPrice = applyPrice;
	}

	public Double getAckPrice() {
		return this.ackPrice;
	}

	public void setAckPrice(Double ackPrice) {
		this.ackPrice = ackPrice;
	}

	public Integer getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Timestamp getOptime() {
		return this.optime;
	}

	public void setOptime(Timestamp optime) {
		this.optime = optime;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}