package com.customerRelation.customerrelation.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.customerRelation.customerrelation.entity.Customer;
import com.customerRelation.customerrelation.exceptions.CustomerNotFound;
import com.customerRelation.customerrelation.service.CustomerServiceImpl;
import com.customerRelation.customerrelation.service.CustomerServiceInterface;

@RestController
@RequestMapping("/abl")

public class CustomerRelationController {
	
	CustomerServiceInterface theCustomerServiceInterface;
	
	public CustomerRelationController(){
		
	}
	
	@Autowired
	public CustomerRelationController(CustomerServiceInterface theCustomerServiceInterface) {
		this.theCustomerServiceInterface = theCustomerServiceInterface;
	}
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomer(){
		return theCustomerServiceInterface.findAll();
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getByIdCustomer(@PathVariable String customerId) {
		Customer theCustomer = this.theCustomerServiceInterface.findById(customerId);
		return theCustomer;
	}
	
	@PostMapping("/customers")
	public ResponseEntity<Object> savingCustomer(@RequestBody Customer theCustomer){
		String id = this.theCustomerServiceInterface.save(theCustomer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@PutMapping("/customers/{customerId}")
	public String UpdatingCustomer(@RequestBody Customer theCustomer, @PathVariable String customerId) {
		String id = this.theCustomerServiceInterface.update(theCustomer, customerId);
		return id;
		
	}

	@GetMapping("/health")
	public String getHealthCheck() {
		return "Up and Running!!";
	}
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteLender(@PathVariable String customerId) {
		
		Customer theCustomer = this.theCustomerServiceInterface.findById(customerId);
		 if(theCustomer ==null){
			 throw new CustomerNotFound("Customer Not found of Id: "+customerId);
		 }
		 this.theCustomerServiceInterface.deleteById(customerId);
		 return "Now Deleted Customer: "+customerId;
	}
	
	
}
