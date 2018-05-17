/**
 * 
 */
package com.sbd.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbd.bean.UserRequest;
import com.sbd.bean.UserResponse;
import com.sbd.service.UserService;
import com.sbd.vo.User;

import io.swagger.annotations.ApiOperation;

/**
 * @author rushikeshM
 * This is One Controller for Project
 * Different Request Method(GET,POST,PUT,DELETE) is Used For Different Purposes
 */
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * Mapping To Fetch All Users
	 */
    @ApiOperation(value = "Get all users",response = Iterable.class)
	@GetMapping("/users")
	public List<UserResponse> getAll(){
		return userService.getAll();
	}
	
	/**
	 * Mapping To Fetch A User By Id
	 */
    @ApiOperation(value = "Get user",response = User.class)
    @GetMapping("/user/{id}")
	public UserResponse getUser(@PathVariable(value = "id", required = true) String id){
		return userService.getUser(id);
	}
	
	/**
	 * Mapping To Add New User
	 */
    @ApiOperation(value = "Add new user",response = User.class)
	@PostMapping("/user")
	public UserResponse addUser(@Valid @RequestBody UserRequest request){
		return userService.addUser(request);
	}
	
	/**
	 * Mapping To Update A User By Id
	 */
    @ApiOperation(value = "Update a user",response = User.class)
    @PutMapping("/user/{id}")
	public UserResponse updateUser(@Valid @RequestBody UserRequest request, @PathVariable(value = "id", required = true) String id){
		return userService.updateUser(request,id);
	}
	
	/**
	 * Mapping To Delete A User By Id
	 */
    @ApiOperation(value = "Delete a user",response = String.class)
    @DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable(value = "id", required = true) String id){
		userService.deleteUser(id);
		return "Success";
	}
    
}
