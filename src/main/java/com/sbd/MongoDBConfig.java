package com.sbd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * @author rushikeshM
 *	This is Configuration Class For Mongo
 *	You Can Write All Configuration Related Code Here
 *	Check "application.properties" in src/main/resources
 */
@Configuration
@PropertySource({ "classpath:application.properties" })
public class MongoDBConfig extends AbstractMongoConfiguration {
	
	@Autowired
	Environment env;
	
	 @Override
	    public String getDatabaseName(){
	        return env.getRequiredProperty("spring.data.mongodb.database");
	    }
	 
	  @Override
	    @Bean
	    public Mongo mongo() throws Exception {

	        ServerAddress serverAddress = new ServerAddress(env.getRequiredProperty("spring.data.mongodb.host"));
	        List<MongoCredential> credentials = new ArrayList<>();
	        credentials.add(MongoCredential.createScramSha1Credential(
	                env.getRequiredProperty("spring.data.mongodb.username"),
	                env.getRequiredProperty("spring.data.mongodb.database"),
	                env.getRequiredProperty("spring.data.mongodb.password").toCharArray()
	        ));
	        MongoClientOptions options = new MongoClientOptions.Builder()
	            .build();
	        return new MongoClient(serverAddress, credentials, options);
	    }

    /*@Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return strings -> {
            userRepository.save(new User("1","Rushikesh","27","Development"));
            userRepository.save(new User("2","Sam","30","Operations"));
        };
    }
*/
}