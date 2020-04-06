package com.candela.forte.integration.db.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the sms_followup_details database table.
 * 
 */
@Entity
@Table(name="sms_followup_details")
@NamedQuery(name="SmsFollowupDetail.findAll", query="SELECT s FROM SmsFollowupDetail s")
public class SmsFollowupDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="first_rem_date")
	private Date firstRemDate;

	@Temporal(TemporalType.DATE)
	@Column(name="second_rem_date")
	private Date secondRemDate;

	//bi-directional many-to-one association to Mobilemaster
	@ManyToOne
	@JoinColumn(name="mobileno")
	private Mobilemaster mobilemaster;

	public SmsFollowupDetail() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFirstRemDate() {
		return this.firstRemDate;
	}

	public void setFirstRemDate(Date firstRemDate) {
		this.firstRemDate = firstRemDate;
	}

	public Date getSecondRemDate() {
		return this.secondRemDate;
	}

	public void setSecondRemDate(Date secondRemDate) {
		this.secondRemDate = secondRemDate;
	}

	public Mobilemaster getMobilemaster() {
		return this.mobilemaster;
	}

	public void setMobilemaster(Mobilemaster mobilemaster) {
		this.mobilemaster = mobilemaster;
	}

}