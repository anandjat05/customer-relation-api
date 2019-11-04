package com.customerRelation.customerrelation.DAO;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.customerRelation.customerrelation.entity.Customer;

public interface MongoInterfaceRepository extends MongoRepository<Customer, String>{
	//extending mongo repository
	@Query("{name : ?0}")
	public List<Customer> findByName(String customerName);
	
	@Query("{id : ?0}")
	public Customer findByLenderId(String customerId);
	
}
