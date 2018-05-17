package com.sbd.repository.impl;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.sbd.repository.ContactRepositoryRedis;
import com.sbd.vo.Contact;

@Repository
public class ContactRepositoryRedisImpl implements ContactRepositoryRedis{

	private static final String KEY = "Contacts";
	
	private RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, String, Contact> hashOperations;
 
	@Autowired
	public ContactRepositoryRedisImpl(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
 
	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}
 
	@Override
	public void save(Contact user)  {
		hashOperations.put(KEY, user.getId(), user);
	}
 
	@Override
	public Contact find(String id) {
		return hashOperations.get(KEY, id);
	}
 
	@Override
	public Map<String, Contact> findAll() {
		return hashOperations.entries(KEY);
	}
 
	@Override
	public void update(Contact user) {
		hashOperations.put(KEY, user.getId(), user);
	}
 
	@Override
	public void delete(String id) {
		hashOperations.delete(KEY, id);
	}
}
