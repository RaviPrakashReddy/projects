package com.candela.forte.integration.db.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the system_properties database table.
 * 
 */
@Entity
@Table(name="system_properties")
@NamedQuery(name="SystemProperty.findAll", query="SELECT s FROM SystemProperty s")
public class SystemProperty implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="property_name")
	private String propertyName;

	@Column(name="property_value")
	private String propertyValue;

	public SystemProperty() {
	}

	public String getPropertyName() {
		return this.propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyValue() {
		return this.propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

}