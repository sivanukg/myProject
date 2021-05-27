package com.demo.api.web.rest.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class DoctorDTO implements Serializable {

	private String id;

	private String firstname;

	private String lastname;

	private String address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
