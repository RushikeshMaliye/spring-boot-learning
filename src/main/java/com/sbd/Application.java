package com.sbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.sbd.repository.UserRepositoryMongo;

/**
 * @author rushikeshM
 * This Class is Entry point of Project
 * Defining "@SpringBootApplication" is must to make Entry point
 * To run this project on Embedded Tomact Server, just right click and RunAs Java Application
 * You Can Also Refer "Readme" File in src/main/resources
 */
@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = UserRepositoryMongo.class)
public class Application  {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
