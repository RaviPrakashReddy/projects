package com.candela.forte.integration.db.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the packagemaster database table.
 * 
 */
@Entity
@NamedQuery(name="Packagemaster.findAll", query="SELECT p FROM Packagemaster p")
public class Packagemaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String packageid;

	private Integer amount;

	private Integer premium;

	public Packagemaster() {
	}

	public String getPackageid() {
		return this.packageid;
	}

	public void setPackageid(String packageid) {
		this.packageid = packageid;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getPremium() {
		return this.premium;
	}

	public void setPremium(Integer premium) {
		this.premium = premium;
	}

}