/**
 * 
 */
package com.sbd.bean;

/**
 * @author rushikeshM
 * This Is User Value Object To Transfer User Data
 */
public class UserResponse {
	
	/**
	 * Fields
	 */
	private String id;
	private String name;
	private String age;
	private String designation;
	
	/**
	 * No-Arg Constructor
	 */
	public UserResponse() {
	}

	/**
	 * Arg Constructor
	 */
	public UserResponse(String id, String name, String age, String designation) {
		super();
		this.id = id;
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
