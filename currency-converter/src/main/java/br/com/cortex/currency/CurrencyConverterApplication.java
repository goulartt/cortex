package br.com.cortex.currency;

import java.util.HashMap;

import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class CurrencyConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConverterApplication.class, args);
	}

	
	@Bean
	public CacheManager cacheManager(RedissonClient redissonClient) {
		var config = new HashMap<String, CacheConfig>();
		//Set time to live to 30 min
		config.put("convert_currency", new CacheConfig(30000, 0));
		return new RedissonSpringCacheManager(redissonClient,config);
	}
}
