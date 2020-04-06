package com.candela.forte.integration.db.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CustomerInfo {

	@NotNull(message = "Mobile number can not be empty")
	@Size(min = 10, message = "Mobile number can not be less than 10 numbers")
	private String mobile;

	@NotNull(message = "Recharge package code can not be empty")
	@Size(min = 1, message = "Recharge package code can not be less than 1 number")
	private String packageCode;

	@NotNull(message = "Recharge timestamp can not be empty")
	@Size(min = 5, message = "Recharge timestamp can not be empty")
	private String rechargeTimeStamp;


}
