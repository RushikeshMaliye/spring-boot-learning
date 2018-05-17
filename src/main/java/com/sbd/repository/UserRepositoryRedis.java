package com.sbd.repository;

import java.util.Map;

import com.sbd.vo.User;

public interface UserRepositoryRedis {
	void save(User user);
	User find(String id);
	Map<String, User> findAll();
	void update(User user);
	void delete(String id);
}
