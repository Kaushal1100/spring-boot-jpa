package com.example.demo.response;

import com.example.demo.entity.Address;

public class AddressResponse {
	private Long id;
	private String street;
	private Long studentId;
	private String firstName;
	private String lastName;
	private String email;
	private String city;
	
	public AddressResponse(Address address) {
		this.id = address.getId();
		this.street = address.getStreet();
		this.city = address.getCity();
		this.studentId = address.getStudent().getId();
		this.firstName = address.getStudent().getFirstName();
		this.lastName = address.getStudent().getLastName();
		this.email = address.getStudent().getEmail();
	}

}
