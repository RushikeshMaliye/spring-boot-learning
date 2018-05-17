/**
 * 
 */
package com.sbd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbd.service.ContactService;
import com.sbd.vo.Contact;

/**
 * @author rushikeshM
 * This is One Controller for Project
 * Different Request Method(GET,POST,PUT,DELETE) is Used For Different Purposes
 */
@RestController
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	/**
	 * Mapping To Fetch All Contacts
	 */
	@RequestMapping(method=RequestMethod.GET,value="/contacts")
	public List<Contact> getAll(){
		return contactService.getAll();
	}
	
	/**
	 * Mapping To Add New Contact
	 */
	@RequestMapping(method=RequestMethod.POST,value="/contact")
	public Contact addContact(@RequestBody Contact contact){
		return contactService.addContact(contact);
	}

	/**
	 * Mapping To Fetch A Contact By Id
	 */
	@RequestMapping(method=RequestMethod.GET,value="/contact/{id}")
	public Contact getContact(@PathVariable String id){
		return contactService.getContact(id);
	}
	
	
	/**
	 * Mapping To Update A Contact By Id
	 */
	@RequestMapping(method=RequestMethod.PUT,value="/contact/{id}")
	public Contact updateContact(@RequestBody Contact contact,@PathVariable String id){
		return contactService.updateContact(contact,id);
	}
	
	/**
	 * Mapping To Delete A Contact By Id
	 */
	@RequestMapping(method=RequestMethod.DELETE,value="/contact/{id}")
	public String deleteContact(@PathVariable String id){
		contactService.deleteContact(id);
		return "Success";
	}

}
