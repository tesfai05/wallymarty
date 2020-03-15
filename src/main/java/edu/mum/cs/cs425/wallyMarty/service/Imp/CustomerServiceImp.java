package edu.mum.cs.cs425.wallyMarty.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.cs425.wallyMarty.model.Customer;
import edu.mum.cs.cs425.wallyMarty.repository.CustomerRepository;
import edu.mum.cs.cs425.wallyMarty.service.ICustomerService;

@Service
public class CustomerServiceImp implements ICustomerService{
	@Autowired
	private CustomerRepository customerRepository;
	@Override
	public List<Customer> getListCustomer() {
		return customerRepository.findAll();
	}

	@Override
	public Customer registerNewCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomer(Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("No customer with Id:" + id));
	}

	@Override
	public void deleteCustomerRegistration(Long id) {
		customerRepository.deleteById(id);
		
	}

	@Override
	public List<Customer> searchStudent(String txt) {
		return customerRepository.findAllCustomerByNameOrCustomerNumberOrPhoneContactNumber(txt, txt, txt);

	}
	
}
