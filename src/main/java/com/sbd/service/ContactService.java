package com.sbd.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbd.exception.EmptyRequestBeanException;
import com.sbd.repository.ContactRepositoryMongo;
import com.sbd.repository.ContactRepositoryRedis;
import com.sbd.vo.Contact;

/**
 * @author rushikeshM
 *	This Is Service Class
 *	All Business Logic Related To Contact, Lay Here
 */
@Service
public class ContactService {
	
	@Autowired
	private ContactRepositoryMongo contactRepositoryMongo;
	
	@Autowired
    private ContactRepositoryRedis contactRepositoryRedis ;
	
	/**
	 * Get All Contacts
	 * @return List<Contact>
	 */
	public List<Contact> getAll(){
		
		List<Contact> contacts=null;
		/**Redis Cache*/
		Map<String, Contact> cachedContacts=contactRepositoryRedis.findAll();
		contacts = cachedContacts.values().stream()
				.map(contact -> new Contact(contact.getId(),contact.getName(), contact.getAge(), contact.getDesignation()))
				.collect(Collectors.toList());

		/**Mongo DB*/
		/*List<User> users=userRepositoryMongo.findAll();
		response=users.stream()
				.map(user -> new UserResponse(user.getId(),user.getName(), user.getAge(), user.getDesignation()))
				.collect(Collectors.toList());*/
		
		
		return contacts;
		//return contactRepositoryMongo.findAll();
	}
	
	/**
	 * Get Contact By Id
	 * @param id
	 * @return Contact
	 */
	public Contact getContact(String id){
		//return contactRepository.findOne(Integer.parseInt(id));
		return contactRepositoryMongo.findOne(id);
	}
	
	/**
	 * Add New Contact
	 * @param contact
	 */
	public Contact addContact(Contact contact){
		
		/**Mongo DB*/
		Contact contact2=new Contact(contact.getName(),contact.getAge(),contact.getDesignation());
		contactRepositoryMongo.save(contact2);
		System.out.println(contact2);
		if(contact2!=null){
			/**Redis Cache*/
			try {
				contactRepositoryRedis.save(contact2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			contact=getContact(contact2.getId());
		}
		return contact;
		
		
		/*if(contact==null){
			throw new EmptyRequestBeanException("Requested JSON is Empty");
		}
		return contactRepositoryMongo.save(contact);*/
	}

	/**
	 * Update Contact By Id
	 * @param contact
	 * @param id
	 */
	public Contact updateContact(Contact contact, String id) {
		if(contact==null){
			throw new EmptyRequestBeanException("Requested JSON is Empty");
		}
		return contactRepositoryMongo.save(contact);
	}

	/**
	 * Delete Contact ById
	 * @param id
	 */
	public void deleteContact(String id) {
		 //contactRepository.delete(Integer.parseInt(id));
		contactRepositoryMongo.delete(id);
	}
	
}
