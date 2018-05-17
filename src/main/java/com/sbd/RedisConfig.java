package com.sbd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ComponentScan("com.sbd.*")
@PropertySource({ "classpath:application.properties" })
public class RedisConfig extends CachingConfigurerSupport {
	
	@Autowired
	Environment env;
	
	@Bean
    public RedisSerializer<String> stringRedisSerializer() {
        return new StringRedisSerializer();
    }
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		/** If we wanna configure connection details, just modify the jedisConnectionFactory configuration */
		 JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
		 	jedisConFactory.setHostName( env.getRequiredProperty("spring.data.redis.hostname"));
		    jedisConFactory.setPort(Integer.parseInt(env.getRequiredProperty("spring.data.redis.port")));
		    jedisConFactory.setTimeout(Integer.parseInt(env.getRequiredProperty("spring.data.redis.timeout")));
		return jedisConFactory;
	}
	 
	/*@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		template.setHashKeySerializer(stringRedisSerializer());
		return template;
	}}*/
	

	@Bean
	public <T> RedisTemplate<String, T> redisTemplate() {
	    RedisTemplate<String, T> redisTemplate = new RedisTemplate<String, T>();
	    redisTemplate.setConnectionFactory(jedisConnectionFactory());
	    redisTemplate.setKeySerializer(stringRedisSerializer());
	    redisTemplate.setHashKeySerializer(stringRedisSerializer());
	    return redisTemplate;
	}
	
}