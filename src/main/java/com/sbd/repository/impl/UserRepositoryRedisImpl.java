package com.sbd.repository.impl;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.sbd.repository.UserRepositoryRedis;
import com.sbd.vo.User;

@Repository
public class UserRepositoryRedisImpl implements UserRepositoryRedis{

	private static final String KEY = "Users";
	
	private RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, String, User> hashOperations;
 
	@Autowired
	public UserRepositoryRedisImpl(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
 
	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}
 
	@Override
	public void save(User user)  {
		hashOperations.put(KEY, user.getId(), user);
	}
 
	@Override
	public User find(String id) {
		return hashOperations.get(KEY, id);
	}
 
	@Override
	public Map<String, User> findAll() {
		return hashOperations.entries(KEY);
	}
 
	@Override
	public void update(User user) {
		hashOperations.put(KEY, user.getId(), user);
	}
 
	@Override
	public void delete(String id) {
		hashOperations.delete(KEY, id);
	}
}
