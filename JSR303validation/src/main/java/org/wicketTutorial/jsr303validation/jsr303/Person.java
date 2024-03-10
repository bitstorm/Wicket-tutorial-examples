/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.wicketTutorial.jsr303validation.jsr303;

import java.io.Serializable;
import java.util.Date;


import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

public class Person implements Serializable{
	@NotNull
	private String name;
	
	@Pattern(regexp = "^[_A-Za-z0-9-]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9-]+)*((.[A-Za-z]{2,}){1}$)")
	private String email;
	
	@Range(min = 18, max = 150)
	private int age;	
	
	@Past @NotNull 
	private Date birthDay;
	
	@NotNull
	private Address address; 
}
