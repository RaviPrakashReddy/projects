package com.candela.epos.solutions.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

@Document
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String USER_KEY_PREFIX = "user::";

	@Id
	@GeneratedValue(strategy = GenerationStrategy.UNIQUE)
	private String key;

	@NotNull
	@Field
	private String username;

	@NotNull
	@Field
	private String password;

	@NotNull
	@Field
	private String agentId;

	@NotNull(message = "First name can not be empty")
	@Size(min = 4, message = "First name can not be less than 4 characters")
	@Field
	private String firstName;

	@NotNull(message = "Last name can not be empty")
	@Size(min = 4, message = "Last name can not be less than 4 characters")
	@Field
	private String lastName;

	@NotNull
	@Field
	private String licenseNum;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z\\s]*$")
	@Size(max = 256)
	@Field
	private String smManager;

	@NotNull
	@Field
	private String branchCode;

	@NotNull
	@Field
	private String branchName;

	@Email
	@Field
	private String email;

	@NotNull
	@Size(min = 10, max = 12)

	@NotNull
	@Field
	private String handPhone;

	public User() {

	}

	public User(String username, String password, String agentId, String firstName, String lastName, String licenseNum,
			String smManager, String branchCode, String branchName, String email, String handPhone) {
		this.username = username;
		this.password = password;
		this.agentId = agentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.licenseNum = licenseNum;
		this.smManager = smManager;
		this.branchCode = branchCode;
		this.branchName = branchName;
		this.email = email;
		this.handPhone = handPhone;
	}

	public static String getKeyFor(String agentId) {
		return USER_KEY_PREFIX + agentId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
		this.key = User.getKeyFor(agentId);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLicenseNum() {
		return licenseNum;
	}

	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}

	public String getSmManager() {
		return smManager;
	}

	public void setSmManager(String smManager) {
		this.smManager = smManager;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHandPhone() {
		return handPhone;
	}

	public void setHandPhone(String handPhone) {
		this.handPhone = handPhone;
	}

	@Override
	public String toString() {
		return "User [key=" + key + ", username=" + username + ", password=" + password + ", agentId=" + agentId
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", licenseNum=" + licenseNum + ", smManager="
				+ smManager + ", branchCode=" + branchCode + ", branchName=" + branchName + ", email=" + email
				+ ", handPhone=" + handPhone + "]";
	}
}
