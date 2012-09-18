package org.wicketTutorial;

import java.io.Serializable;

public class RegistrationInfo implements Serializable {
	
	private String name;
	private String surname;
	private String address;
	private String email;
	private boolean subscribeList;
	
	/*Getters and setters*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isSubscribeList() {
		return subscribeList;
	}
	public void setSubscribeList(boolean subscribeList) {
		this.subscribeList = subscribeList;
	}
}
