package com.candela.forte.integration.db.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the insurecovermaster database table.
 * 
 */
@Entity
@NamedQuery(name="Insurecovermaster.findAll", query="SELECT i FROM Insurecovermaster i")
public class Insurecovermaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer premium;

	private Integer accidentcover;

	private Integer hospitalcover;

	public Insurecovermaster() {
	}

	public Integer getPremium() {
		return this.premium;
	}

	public void setPremium(Integer premium) {
		this.premium = premium;
	}

	public Integer getAccidentcover() {
		return this.accidentcover;
	}

	public void setAccidentcover(Integer accidentcover) {
		this.accidentcover = accidentcover;
	}

	public Integer getHospitalcover() {
		return this.hospitalcover;
	}

	public void setHospitalcover(Integer hospitalcover) {
		this.hospitalcover = hospitalcover;
	}

}