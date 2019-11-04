package com.customerRelation.customerrelation.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
public class Customer {
	
	@Id
	private String id;
	@Indexed(direction = IndexDirection.ASCENDING)
	private String name;
	private String email;
	private String cell;
	private Address address;
	private Date dateCreated;
	private Date dateUpdated;
	
	public Customer() {
		//default constructor
	}
	@PersistenceConstructor
	public Customer(String id, String name, String email, String cell, Address address, Date dateCreated, Date dateUpdated) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.cell = cell;
		this.address = address;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", cell=" + cell + ", address=" + address
				+ "]";
	}
	
}
