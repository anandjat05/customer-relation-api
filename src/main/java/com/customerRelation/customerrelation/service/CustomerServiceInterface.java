package com.customerRelation.customerrelation.service;

import java.util.List;

import com.customerRelation.customerrelation.entity.Customer;

public interface CustomerServiceInterface {
	
	public List<Customer> findAll();
	public Customer findById(String theId);
	public String save(Customer theCustomer);
	public String  update(Customer theCustomer, String theId);
	public String deleteById(String theId);
	
}
