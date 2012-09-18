package org.wicketTutorial;

import java.io.Serializable;
import java.util.List;

public class Person implements Serializable {
	private String name;
	private String surname;
	private String address;
	private String email;
	private String passportCode;
	
	private Person spouse;
	private List<Person> children;
       
	public String getFullName(){
	   return name + " " + surname;
	}
	
	public Person(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

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

	public String getPassportCode() {
		return passportCode;
	}

	public void setPassportCode(String passportCode) {
		this.passportCode = passportCode;
	}

	public Person getSpouse() {
		return spouse;
	}

	public void setSpouse(Person spouse) {
		this.spouse = spouse;
	}

	public List<Person> getChildren() {
		return children;
	}

	public void setChildren(List<Person> children) {
		this.children = children;
	} 
	
	
}
