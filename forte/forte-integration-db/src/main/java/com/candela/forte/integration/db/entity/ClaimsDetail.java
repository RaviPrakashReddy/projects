package com.candela.forte.integration.db.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;


/**
 * The persistent class for the claims_details database table.
 * 
 */
@Entity
@Table(name="claims_details")
@NamedQuery(name="ClaimsDetail.findAll", query="SELECT c FROM ClaimsDetail c")
public class ClaimsDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="claim_id")
	private Long claimId;

	@Column(name="bank_account_number")
	private String bankAccountNumber;

	@Column(name="bank_name")
	private String bankName;

	@Column(name="bic_swift_code")
	private String bicSwiftCode;

	@Column(name="claim_amount")
	private double claimAmount;

	@Column(name="claim_details")
	private String claimDetails;

	@Column(name="claim_type")
	private String claimType;

	@Column(name="claims_status")
	private String claimsStatus;

	private String diagnosis;

	@Column(name="document_checklist")
	private String documentChecklist;

	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="event_end_date")
	private Date eventEndDate;

	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="event_start_date")
	private Date eventStartDate;

	@Column(name="hospital_name")
	private String hospitalName;

	@Column(name="internal_comments")
	private String internalComments;

	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="notification_date")
	private Date notificationDate;

	@Column(name="payment_mode")
	private String paymentMode;

	@Transient
	private String mobileNumber;
	
	//bi-directional many-to-one association to Mobilemaster
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="mobileno")
	private Mobilemaster mobilemaster;

	public ClaimsDetail() {
	}

	public Long getClaimId() {
		return this.claimId;
	}

	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}

	public String getBankAccountNumber() {
		return this.bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBicSwiftCode() {
		return this.bicSwiftCode;
	}

	public void setBicSwiftCode(String bicSwiftCode) {
		this.bicSwiftCode = bicSwiftCode;
	}

	public double getClaimAmount() {
		return this.claimAmount;
	}

	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}

	public String getClaimDetails() {
		return this.claimDetails;
	}

	public void setClaimDetails(String claimDetails) {
		this.claimDetails = claimDetails;
	}

	public String getClaimType() {
		return this.claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public String getClaimsStatus() {
		return this.claimsStatus;
	}

	public void setClaimsStatus(String claimsStatus) {
		this.claimsStatus = claimsStatus;
	}

	public String getDiagnosis() {
		return this.diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getDocumentChecklist() {
		return this.documentChecklist;
	}

	public void setDocumentChecklist(String documentChecklist) {
		this.documentChecklist = documentChecklist;
	}

	public Date getEventEndDate() {
		return this.eventEndDate;
	}

	public void setEventEndDate(Date eventEndDate) {
		this.eventEndDate = eventEndDate;
	}

	public Date getEventStartDate() {
		return this.eventStartDate;
	}

	public void setEventStartDate(Date eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	public String getHospitalName() {
		return this.hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getInternalComments() {
		return this.internalComments;
	}

	public void setInternalComments(String internalComments) {
		this.internalComments = internalComments;
	}

	public Date getNotificationDate() {
		return this.notificationDate;
	}

	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}

	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Mobilemaster getMobilemaster() {
		return this.mobilemaster;
	}

	public void setMobilemaster(Mobilemaster mobilemaster) {
		this.mobilemaster = mobilemaster;
	}

	public String getMobileNumber() {
		if(mobilemaster!=null)
			return mobilemaster.getMobileno();
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	

}