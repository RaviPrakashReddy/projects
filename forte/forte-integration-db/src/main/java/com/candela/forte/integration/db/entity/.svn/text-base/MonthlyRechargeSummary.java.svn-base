package com.candela.forte.integration.db.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the monthly_recharge_summary database table.
 * 
 */
@Entity
@Table(name="monthly_recharge_summary")
@NamedQuery(name="MonthlyRechargeSummary.findAll", query="SELECT m FROM MonthlyRechargeSummary m")
public class MonthlyRechargeSummary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="monthly_summary_id")
	private Long monthlySummaryId;

	@Column(name="carry_forward_amount")
	private Integer carryForwardAmount;

	@Column(name="date_formate")
	private String dateFormate;

	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="last_modified_date")
	private Date lastModifiedDate;

	private String mobileno;

	@Column(name="month_reference_number")
	private String monthReferenceNumber;

	@JsonProperty("insurencePremiumAmount")
	@Column(name="month_total_recharge")
	private Integer monthTotalRecharge;

	@Column(name="monthly_premium_amount")
	private Integer monthlyPremiumAmount;

	@Column(name="monthly_recharge_amount")
	private Integer monthlyRechargeAmount;

	@Column(name="previous_month_carryforward_amount")
	private Integer previousMonthCarryforwardAmount;

	@Column(name="sent_sms")
	private String sentSms;

	public MonthlyRechargeSummary() {
	}

	public Long getMonthlySummaryId() {
		return this.monthlySummaryId;
	}

	public void setMonthlySummaryId(Long monthlySummaryId) {
		this.monthlySummaryId = monthlySummaryId;
	}

	public Integer getCarryForwardAmount() {
		return this.carryForwardAmount;
	}

	public void setCarryForwardAmount(Integer carryForwardAmount) {
		this.carryForwardAmount = carryForwardAmount;
	}

	public String getDateFormate() {
		return this.dateFormate;
	}

	public void setDateFormate(String dateFormate) {
		this.dateFormate = dateFormate;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getMobileno() {
		return this.mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getMonthReferenceNumber() {
		return this.monthReferenceNumber;
	}

	public void setMonthReferenceNumber(String monthReferenceNumber) {
		this.monthReferenceNumber = monthReferenceNumber;
	}

	public Integer getMonthTotalRecharge() {
		return this.monthTotalRecharge;
	}

	public void setMonthTotalRecharge(Integer monthTotalRecharge) {
		this.monthTotalRecharge = monthTotalRecharge;
	}

	public Integer getMonthlyPremiumAmount() {
		return this.monthlyPremiumAmount;
	}

	public void setMonthlyPremiumAmount(Integer monthlyPremiumAmount) {
		this.monthlyPremiumAmount = monthlyPremiumAmount;
	}

	public Integer getMonthlyRechargeAmount() {
		return this.monthlyRechargeAmount;
	}

	public void setMonthlyRechargeAmount(Integer monthlyRechargeAmount) {
		this.monthlyRechargeAmount = monthlyRechargeAmount;
	}

	public Integer getPreviousMonthCarryforwardAmount() {
		return this.previousMonthCarryforwardAmount;
	}

	public void setPreviousMonthCarryforwardAmount(Integer previousMonthCarryforwardAmount) {
		this.previousMonthCarryforwardAmount = previousMonthCarryforwardAmount;
	}

	public String getSentSms() {
		return this.sentSms;
	}

	public void setSentSms(String sentSms) {
		this.sentSms = sentSms;
	}

}