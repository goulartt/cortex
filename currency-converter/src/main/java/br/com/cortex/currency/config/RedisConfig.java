package br.com.cortex.currency.config;

import java.util.HashMap;

import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {
	
	@Value("${cache.ttl}")
	private long ttl;
	
	@Bean
	public CacheManager cacheManager(RedissonClient redissonClient) {
		var config = new HashMap<String, CacheConfig>();
		//Set time to live to 30 min
		config.put("convert_currency", new CacheConfig(ttl*1000, 0));
		return new RedissonSpringCacheManager(redissonClient,config);
	}
}
