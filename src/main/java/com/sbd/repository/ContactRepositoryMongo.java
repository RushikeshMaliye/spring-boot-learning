package com.sbd.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sbd.vo.Contact;
/**
 * @author rushikeshM
 *	This Is Repository Class For MongoDB
 *	Basic CRUD Operation Methods Already Written in Extended "MongoRepository<User,Integer>"
 *	If Your Requirement Doesn't Match With Existing Methods, You Can Write Customize Mongo Query In This Interface 
 */
public interface ContactRepositoryMongo extends MongoRepository<Contact, String> {
	
}
