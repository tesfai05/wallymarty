package edu.mum.cs.cs425.wallyMarty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.cs.cs425.wallyMarty.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	List<Customer> findAllCustomerByNameOrCustomerNumberOrPhoneContactNumber(
			String name,
			String customerNumber,
			String phoneContactNumber);
	
	}
