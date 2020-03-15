package edu.mum.cs.cs425.wallyMarty.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.cs.cs425.wallyMarty.model.Customer;
import edu.mum.cs.cs425.wallyMarty.service.Imp.CustomerServiceImp;

@Controller
public class CustomerController {
	@Autowired
	private CustomerServiceImp customertService;

	@GetMapping(value = { "/", "/wallymarty" })
	public String displayRegistrarHomePage() {
		return "home/index";

	}

	@GetMapping(value = { "/customers/list" })
	public ModelAndView displayListCustomers(ModelAndView model) {
		List<Customer> customers = customertService.getListCustomer()
				.stream()
				.sorted(Comparator.comparing(Customer::getName))
				.collect(Collectors.toList());
		model.addObject("customers", customers);
		model.addObject("customersCount", customers.size());
		model.setViewName("customer/list");
		return model;
	}

	@GetMapping(value = { "/primecustomers/list" })
	public String displayListPrimeCustomers(Model model) {

		List<Customer> customers = customertService.getListCustomer().stream()
				.filter(cst -> Period.between(cst.getDateOfBirth(),LocalDate.now()).getYears()> 40)
				.sorted(Comparator.comparing(Customer::getDateOfBirth))
				.collect(Collectors.toList());
			
		model.addAttribute("customers", customers);
		model.addAttribute("customersCount", customers.size());
		return "customer/primelist";

	}

	@GetMapping(value = { "/customers/new" })
	public String addNewCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer/new";
	}

	@PostMapping(value = { "/customers/new" })
	public String saveNewCustomer(Model model, @Valid @ModelAttribute("customer") Customer customer,
			BindingResult result) {
		model.addAttribute("customer", customer);
		if (result.hasErrors()) {

			return "customer/new";
		}

		customertService.registerNewCustomer(customer);

		return "redirect:/customers/list";

	}

	@GetMapping(value = "/editcustomer/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		Customer customer = customertService.getCustomer(id);
		if (customer == null) {
			return "redirect:/customers/list";
		}
		model.addAttribute("customer", customer);
		return "customer/new";
	}

	@GetMapping(value = "/deletecustomer/{id}")
	public String deleteCustomer(@PathVariable Long id, Model model) {
		customertService.deleteCustomerRegistration(id);
		return "redirect:/customers/list";
	}

	@GetMapping(value = "/customers/search")
	public String searchStudent(Model model, @RequestParam("txt") String txt) {
		List<Customer> customers = customertService.searchStudent(txt);
		model.addAttribute("customers", customers);
		model.addAttribute("txt", txt);
		model.addAttribute("customersCount", customers.size());
		return "customer/list";
	}

}
