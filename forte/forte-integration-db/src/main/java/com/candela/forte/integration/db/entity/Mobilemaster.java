package com.candela.forte.integration.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the mobilemaster database table.
 * 
 */
@Entity
@NamedQuery(name="Mobilemaster.findAll", query="SELECT m FROM Mobilemaster m")
public class Mobilemaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String mobileno;

	private String casecreated;

	private String infopresent;

	@Temporal(TemporalType.DATE)
	@Column(name="register_date")
	private Date registerDate;

	//bi-directional many-to-one association to CustomerDetail
	@OneToMany(mappedBy="mobilemaster",cascade=CascadeType.ALL)
	private Set<CustomerDetail> customerDetails;

	//bi-directional many-to-one association to Rechargedetail
	@OneToMany(mappedBy="mobilemaster",cascade=CascadeType.ALL)
	private Set<Rechargedetail> rechargedetails;

	//bi-directional many-to-one association to SmsFollowupDetail
	@OneToMany(mappedBy="mobilemaster")
	private Set<SmsFollowupDetail> smsFollowupDetails;
	
	//bi-directional many-to-one association to ClaimsDetail
	@OneToMany(mappedBy="mobilemaster",cascade=CascadeType.ALL)
	private Set<ClaimsDetail> claimsDetails;

	public Mobilemaster() {
	}

	public String getMobileno() {
		return this.mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getCasecreated() {
		return this.casecreated;
	}

	public void setCasecreated(String casecreated) {
		this.casecreated = casecreated;
	}

	public String getInfopresent() {
		return this.infopresent;
	}

	public void setInfopresent(String infopresent) {
		this.infopresent = infopresent;
	}

	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Set<CustomerDetail> getCustomerDetails() {
		return this.customerDetails;
	}

	public void setCustomerDetails(Set<CustomerDetail> customerDetails) {
		this.customerDetails = customerDetails;
	}

	public CustomerDetail addCustomerDetail(CustomerDetail customerDetail) {
		getCustomerDetails().add(customerDetail);
		customerDetail.setMobilemaster(this);

		return customerDetail;
	}

	public CustomerDetail removeCustomerDetail(CustomerDetail customerDetail) {
		getCustomerDetails().remove(customerDetail);
		customerDetail.setMobilemaster(null);

		return customerDetail;
	}

	public Set<Rechargedetail> getRechargedetails() {
		return this.rechargedetails;
	}

	public void setRechargedetails(Set<Rechargedetail> rechargedetails) {
		this.rechargedetails = rechargedetails;
	}

	public Rechargedetail addRechargedetail(Rechargedetail rechargedetail) {
		getRechargedetails().add(rechargedetail);
		rechargedetail.setMobilemaster(this);

		return rechargedetail;
	}

	public Rechargedetail removeRechargedetail(Rechargedetail rechargedetail) {
		getRechargedetails().remove(rechargedetail);
		rechargedetail.setMobilemaster(null);

		return rechargedetail;
	}

	public Set<SmsFollowupDetail> getSmsFollowupDetails() {
		return this.smsFollowupDetails;
	}

	public void setSmsFollowupDetails(Set<SmsFollowupDetail> smsFollowupDetails) {
		this.smsFollowupDetails = smsFollowupDetails;
	}

	public SmsFollowupDetail addSmsFollowupDetail(SmsFollowupDetail smsFollowupDetail) {
		getSmsFollowupDetails().add(smsFollowupDetail);
		smsFollowupDetail.setMobilemaster(this);

		return smsFollowupDetail;
	}

	public SmsFollowupDetail removeSmsFollowupDetail(SmsFollowupDetail smsFollowupDetail) {
		getSmsFollowupDetails().remove(smsFollowupDetail);
		smsFollowupDetail.setMobilemaster(null);

		return smsFollowupDetail;
	}

	public Set<ClaimsDetail> getClaimsDetails() {
		return this.claimsDetails;
	}

	public void setClaimsDetails(Set<ClaimsDetail> claimsDetails) {
		this.claimsDetails = claimsDetails;
	}
}