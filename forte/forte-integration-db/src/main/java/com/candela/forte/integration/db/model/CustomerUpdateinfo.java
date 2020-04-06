package com.candela.forte.integration.db.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CustomerUpdateinfo {

	@NotNull(message = "Mobile number can not be empty")
	@Size(min = 10, message = "Mobile number can not be less than 10 numbers")
	private String mobile;

	private String preferredLanguage;

	@NotNull(message = "First name can not be empty")
	@Size(min = 4, message = "First name can not be less than 4 characters")
	private String firstName;

	@NotNull(message = "Last name can not be empty")
	@Size(min = 4, message = "Last name can not be less than 4 characters")
	private String lastName;

	@NotNull(message = "Gender can not be empty")
	@Size(min = 4, message = "Gender can not be less than 4 characters")
	private String gender;

	@NotNull(message = "Date of Birth can not be empty")
	@Size(min = 8, message = "Date of Birth can not be empty")
	private String dob;

	@NotNull(message = "ID type can not be empty")
	@Size(min = 6, message = "ID type can not be less than 6 characters")
	private String idType;

	@NotNull(message = "ID number can not be empty")
	@Size(min = 10, message = "ID number can not be less than 10 numbers")
	private String idNumber;

	private String nomineeName;
	private String nomineeRelation;
	private String nomineeContactNum;
	private String updatedBy;

}
