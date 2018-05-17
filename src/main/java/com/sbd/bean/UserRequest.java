/**
 * 
 */
package com.sbd.bean;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author rushikeshM
 * This Is User Request Object To Transfer User Data
 */
public class UserRequest {
	/**
	 * Fields
	 */
	@ApiModelProperty(value = "name", dataType = "java.lang.String", required = true)
	@NotNull
	@NotEmpty
	private String name;
	
	@ApiModelProperty(value = "age", dataType = "java.lang.String", required = true)
	@NotNull
	@NotEmpty
	private String age;
	
	@ApiModelProperty(value = "designation", dataType = "java.lang.String", required = true)
	@NotNull
	@NotEmpty
	private String designation;
	
	/**
	 * No-Arg Constructor
	 */
	public UserRequest() {
	}

	/**
	 * Arg Constructor
	 */
	public UserRequest(String name, String age, String designation) {
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

	@Override
	public String toString() {
		return "UserRequest [name=" + name + ", age=" + age + ", designation=" + designation + "]";
	}
}
