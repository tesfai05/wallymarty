package edu.mum.cs.cs425.wallyMarty.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	
	//@NotNull(message = "# please student Number")
	//@Column(nullable = false)
	//@Pattern(regexp = "(0){3}-([0-9]){2}-([0-9]){4}",message = "# please provide a valid Student Id")
	private String customerNumber;
	
	//@NotNull(message = "# please Provide First Name")
	//@Column(nullable = false)
	private String name ;
	
	//@NotNull(message = "# please Phone Contact Number")
	//@Column(nullable = false)
	private String phoneContactNumber ;
	
	//@NotNull(message = "# please Provide Date of Birth")
	//@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	

	public Customer() {
		
	}
	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public String getCustomerNumber() {
		return customerNumber;
	}


	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhoneContactNumber() {
		return phoneContactNumber;
	}


	public void setPhoneContactNumber(String phoneContactNumber) {
		this.phoneContactNumber = phoneContactNumber;
	}


	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	

}
