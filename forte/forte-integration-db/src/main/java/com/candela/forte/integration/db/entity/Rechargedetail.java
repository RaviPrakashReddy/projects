package com.candela.forte.integration.db.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;


/**
 * The persistent class for the rechargedetails database table.
 * 
 */
@Entity
@Table(name="rechargedetails")
@NamedQuery(name="Rechargedetail.findAll", query="SELECT r FROM Rechargedetail r")
public class Rechargedetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="monthly_reference_number")
	private String monthlyReferenceNumber;

	private String packageid;

	private Timestamp rechargedate;
	
	private Integer premium;

	@Column(name="recharge_amount")
	private Integer rechargeAmount;

	
	//bi-directional many-to-one association to Mobilemaster
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="mobileno")
	private Mobilemaster mobilemaster;

	public Rechargedetail() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMonthlyReferenceNumber() {
		return this.monthlyReferenceNumber;
	}

	public void setMonthlyReferenceNumber(String monthlyReferenceNumber) {
		this.monthlyReferenceNumber = monthlyReferenceNumber;
	}

	public String getPackageid() {
		return this.packageid;
	}

	public void setPackageid(String packageid) {
		this.packageid = packageid;
	}

	public Timestamp getRechargedate() {
		return this.rechargedate;
	}

	public void setRechargedate(Timestamp rechargedate) {
		this.rechargedate = rechargedate;
	}

	public Mobilemaster getMobilemaster() {
		return this.mobilemaster;
	}

	public void setMobilemaster(Mobilemaster mobilemaster) {
		this.mobilemaster = mobilemaster;
	}
	
	public Integer getPremium() {
		return this.premium;
	}

	public void setPremium(Integer premium) {
		this.premium = premium;
	}

	public Integer getRechargeAmount() {
		return this.rechargeAmount;
	}

	public void setRechargeAmount(Integer rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
}