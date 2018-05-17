package com.sbd.repository;

import java.util.Map;

import com.sbd.vo.Contact;

public interface ContactRepositoryRedis {
	void save(Contact contact);
	Contact find(String id);
	Map<String, Contact> findAll();
	void update(Contact contact);
	void delete(String id);
}
