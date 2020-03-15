package edu.mum.cs.cs425.wallyMarty.service;

import java.util.List;

import edu.mum.cs.cs425.wallyMarty.model.Customer;


public interface ICustomerService {
	List<Customer> getListCustomer();
	Customer registerNewCustomer(Customer customer);
	List<Customer> searchStudent(String txt);
	Customer getCustomer(Long id);
	void deleteCustomerRegistration(Long id);
}

