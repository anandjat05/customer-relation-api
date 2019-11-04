package com.customerRelation.customerrelation.service;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.customerRelation.customerrelation.DAO.MongoInterfaceRepository;
import com.customerRelation.customerrelation.entity.Customer;
import com.customerRelation.customerrelation.exceptions.CustomerNotFound;

@Service
@Component()
public class CustomerServiceImpl implements CustomerServiceInterface {
	
	private MongoInterfaceRepository mongoInterfaceRepository;
	
	public CustomerServiceImpl() {
		
	}
	
	@Autowired
	public CustomerServiceImpl(MongoInterfaceRepository mongoInterfaceRepository) {
		this.mongoInterfaceRepository = mongoInterfaceRepository;
	}

	@Override
	public List<Customer> findAll() {
		List<Customer> allCustomer = this.mongoInterfaceRepository.findAll(Sort.by(Direction.DESC, "name"));
		return allCustomer;
	}

	@Override
	public Customer findById(String theId) {
		Optional<Customer> result = this.mongoInterfaceRepository.findById(theId);
		Customer theCustomer = null;
		if(result.isPresent()) {
			theCustomer = result.get();
		}else {
			throw new CustomerNotFound("The Customer with id "+theId+" is not found!!");
		}
	
		return theCustomer;
	}

	@Override
	public String save(Customer theCustomer) {
		//Saving the data
		theCustomer.setDateCreated(Calendar.getInstance().getTime());
		theCustomer.setDateUpdated(Calendar.getInstance().getTime());
		this.mongoInterfaceRepository.insert(theCustomer);
		return theCustomer.getId();
	}

	@Override
	public String update(Customer theCustomer, String theId) {
		theCustomer.setDateUpdated(Calendar.getInstance().getTime());
		this.mongoInterfaceRepository.save(theCustomer);
		return theCustomer.getId();
	}

	@Override
	public String deleteById(String theId) {
		this.mongoInterfaceRepository.deleteById(theId);
		return theId;
	}

}
