package com.sbd.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbd.bean.UserRequest;
import com.sbd.bean.UserResponse;
import com.sbd.exception.UserNotFoundException;
import com.sbd.repository.UserRepositoryMongo;
import com.sbd.repository.UserRepositoryRedis;
import com.sbd.vo.User;

/**
 * @author rushikeshM
 *	This Is Service Class
 *	All Business Logic Related To User, Lay Here
 */
@Service
public class UserService {
	
	@Autowired
	private UserRepositoryMongo userRepositoryMongo;
	
	@Autowired
    private UserRepositoryRedis userRepositoryRedis ;
	
	/**
	 * Collection name of MongoDB
	 */
	private static final String HOSTING_SEQ_KEY = "hosting";
	
	/**
	 * Get All Users
	 * Fetching all users from Redis server only
	 * @return List<User>
	 */
	public List<UserResponse> getAll(){
		
		List<UserResponse> response=null;
		
		/**Redis Cache*/
		Map<String, User> cachedUsers=userRepositoryRedis.findAll();
		response = cachedUsers.values().stream()
				.map(user -> new UserResponse(user.getId(),user.getName(), user.getAge(), user.getDesignation()))
				.collect(Collectors.toList());

		/**Mongo DB*/
		/*List<User> users=userRepositoryMongo.findAll();
		response=users.stream()
				.map(user -> new UserResponse(user.getId(),user.getName(), user.getAge(), user.getDesignation()))
				.collect(Collectors.toList());*/
		
		return response;
	}
	
	/**
	 * Get User By Id
	 * Fetching user from redis server
	 * @param id
	 * @return UserResponse
	 */
	public UserResponse getUser(String id){
		
		UserResponse response=null;
		
		/**Redis Cache*/
		User cachedUser=userRepositoryRedis.find(id);
			if(cachedUser!=null){
				response=new UserResponse(cachedUser.getId(), cachedUser.getName(), cachedUser.getAge(), cachedUser.getDesignation());
			}else{
				User user=userRepositoryMongo.findOne(id);
				if(user!=null){
					userRepositoryRedis.save(user);
					getUser(user.getId());
				}else{
					throw new UserNotFoundException("User Does not Exist");
				}
			}
		return response;
	}
	
	/**
	 * Add New User
	 * @param UserRequest
	 * @return UserResponse
	 */
	public UserResponse addUser(UserRequest request){
		UserResponse response=null;
			/**Mongo DB*/
			User user=new User(request.getName(),request.getAge(),request.getDesignation());
			user=userRepositoryMongo.save(user);
			System.out.println(user);
			if(user!=null){
				/**Redis Cache*/
				userRepositoryRedis.save(user);
				response=getUser(user.getId());
			}
		return response;
	}

	/**
	 * Update User By Id
	 * @param UserRequest
	 * @param id
	 * @return UserResponse
	 */
	public UserResponse updateUser(UserRequest request,String id) {
		UserResponse response=null;
		
		/**Mongo DB*/
		 if(userRepositoryMongo.exists(id)){
			 User user= userRepositoryMongo.findOne(id);
			 user.setName(request.getName());
			 user.setAge(request.getAge());
			 user.setDesignation(request.getDesignation());
			 user=userRepositoryMongo.save(user);
			 if(user!=null){
				 User cachedUser=userRepositoryRedis.find(id);
				 if(cachedUser!=null){
					cachedUser.setName(request.getName());
					cachedUser.setAge(request.getAge());
					cachedUser.setDesignation(request.getDesignation());
					userRepositoryRedis.update(cachedUser);
					response=getUser(cachedUser.getId());
				 }else{
					 userRepositoryRedis.save(user);
					 response=getUser(cachedUser.getId());
				 }
			 }else{
				 //logger
			 }
			 return response;
		 }else{
			 throw new UserNotFoundException("User Not Found");
		 }
		 
	}
	/**
	 * Delete User ById
	 * @param id
	 */
	public void deleteUser(String id) {
		/**Redis Cache*/
		User user= userRepositoryMongo.findOne(id);
		if(user!=null){
			/**Mongo DB*/
			userRepositoryMongo.delete(id);
			User cachedUser=userRepositoryRedis.find(id);
			if(cachedUser!=null){
				userRepositoryRedis.delete(cachedUser.getId());
			}
		}else{
			User cachedUser=userRepositoryRedis.find(id);
			if(cachedUser!=null){
			userRepositoryRedis.delete(cachedUser.getId());
			}
		}
	}
}
