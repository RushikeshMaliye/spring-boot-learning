package com.sbd.vo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Contact implements Serializable{

	/**
	 * Fields
	 */
	@Id
	private String id;
	private String name;
	private String age;
	private String designation;
	
	/**
	 * No-Arg Constructor
	 */
	public Contact() {
	}

	/**
	 * Arg Constructor
	 */
	public Contact(String id, String name, String age, String designation) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.designation = designation;
	}

	/**
	 * Arg Constructor
	 */
	public Contact(String name, String age, String designation) {
		super();
		this.name = name;
		this.age = age;
		this.designation = designation;
	}

	/**
	 * Setter & Getter For Fields
	 */

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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	/**
	 * ToString Method To Print User Object
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", designation=" + designation + "]";
	}



}
