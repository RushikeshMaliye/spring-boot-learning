/**
 * 
 */
package com.sbd.vo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * @author rushikeshM
 * This Is User Value Object To Transfer User Data
 */
@Document(collection="sbdUsers")
public class User implements Serializable{
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
	public User() {
	}

	/**
	 * Arg Constructor
	 */
	public User(String id, String name, String age, String designation) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.designation = designation;
	}
	
	public User(String name, String age, String designation) {
		super();
		this.name = name;
		this.age = age;
		this.designation = designation;
	}

	/**
	 * Setter & Getter For Fields
	 */
	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
