package com.candela.forte.integration.db.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the customer_details database table.
 * 
 */
@Entity
@Table(name="customer_details")
@NamedQuery(name="CustomerDetail.findAll", query="SELECT c FROM CustomerDetail c")
public class CustomerDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id")
	private Long customerId;

	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dob;

	@Column(name="first_name")
	private String firstName;

	private String gender;

	@Column(name="id_number")
	private String idNumber;

	@Column(name="id_type")
	private String idType;

	@Column(name="last_name")
	private String lastName;

	@Column(name="last_updated_date")
	private Timestamp lastUpdatedDate;

	@Column(name="nominee_contact_no")
	private String nomineeContactNo;

	@Column(name="nominee_name")
	private String nomineeName;

	@Column(name="pref_lang")
	private String prefLang;

	@Column(name="relation_nominee")
	private String relationNominee;

	@Column(name="updated_by")
	private String updatedBy;

	@JsonIgnore
	//bi-directional many-to-one association to Mobilemaster
	@ManyToOne
	@JoinColumn(name="mobileno")
	private Mobilemaster mobilemaster;

	public CustomerDetail() {
	}

	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getIdType() {
		return this.idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Timestamp getLastUpdatedDate() {
		return this.lastUpdatedDate;
	}

	public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getNomineeContactNo() {
		return this.nomineeContactNo;
	}

	public void setNomineeContactNo(String nomineeContactNo) {
		this.nomineeContactNo = nomineeContactNo;
	}

	public String getNomineeName() {
		return this.nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	public String getPrefLang() {
		return this.prefLang;
	}

	public void setPrefLang(String prefLang) {
		this.prefLang = prefLang;
	}

	public String getRelationNominee() {
		return this.relationNominee;
	}

	public void setRelationNominee(String relationNominee) {
		this.relationNominee = relationNominee;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Mobilemaster getMobilemaster() {
		return this.mobilemaster;
	}

	public void setMobilemaster(Mobilemaster mobilemaster) {
		this.mobilemaster = mobilemaster;
	}

}