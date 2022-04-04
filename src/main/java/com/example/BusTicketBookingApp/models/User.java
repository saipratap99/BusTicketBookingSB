package com.example.BusTicketBookingApp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "first_name", length = 30, nullable = false)
	@NotBlank
	@Size(min = 2, message = "must be atleast 2 characters")
	private String firstName;
	
	@Column(name = "last_name", length = 30)
	@NotBlank
	@Size(min = 2, message = "must be atleast 2 characters")
	private String lastName;
	
	@Column(nullable = false, unique = true)
	@Email
	private String email;
	
	@NotBlank
	@Size(min = 3, message = "must be atleast 2 characters")
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@Column(name="mobile_numer", unique = true)
	private String mobileNumber;
	
	@Column(columnDefinition = "varchar(50) default 'ROLE_USER'")
	private String role;
	
	@Column(name = "is_activated", columnDefinition = "boolean default false")
	private boolean isActivated;
	
	@Column(nullable = true)
	private int OTP;
	
//	created_at, updated_at
	
	
	public User() {}
	
	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = "ROLE_USER";
	}
	
	public int getId() {
		return id;	
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean isActivated() {
		return isActivated;
	}
	
	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}
	
	public int getOTP() {
		return OTP;
	}
	
	public void setOTP(int oTP) {
		OTP = oTP;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
