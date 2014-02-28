package org.wicketTutorial.jsr303;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

public class Person {
	private String name;
	
	private String email;
	
	private int age;	
	
	@Past @NotNull 
	private Date birthDay;
}
