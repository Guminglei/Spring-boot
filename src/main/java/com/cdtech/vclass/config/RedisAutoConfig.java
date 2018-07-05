/**   
 * <p>Title: RedisAutoConfig.java</p>
 * @Package com.hello.boot1.config 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海策道科技信息服务有限公司</p>
 * @author guminglei
 * @since 2017年10月31日 上午10:07:45 
 * @version V1.0   
 */
package com.cdtech.vclass.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

/** 
 * <p>Description:redis配置</p> 
 * <p>Company:上海策道科技信息服务有限公司</p>
 * @author guminglei
 * @version V1.0 
 */

@Configuration
public class RedisAutoConfig {
	
	@Value("${spring.redis.boot1.database}")
	private int redis_database;
	@Value("${spring.redis.boot1.host-name}")
	private String redis_host;
	@Value("${spring.redis.boot1.port}")
	private int redis_port;
	@Value("${spring.redis.password}")
	private String redis_pass;
	@Value("${spring.redis.boot1.pool.max-active}")
	private int redis_pool_max_active;
	@Value("${spring.redis.boot1.pool.max-wait}")
	private int redis_pool_max_wait;
	@Value("${spring.redis.boot1.pool.max-idle}")
	private int redis_pool_max_idle;
	@Value("${spring.redis.boot1.pool.min-idle}")
	private int redis_pool_min_idle;
	@Value("${spring.redis.boot1.timeout}")
	private int redis_timeout;
	
	@Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.redis.boot1")
    public RedisConnectionFactory redisConnectionFactory() {
    	JedisConnectionFactory jedisConnectionFactory=new JedisConnectionFactory();
    	jedisConnectionFactory.setDatabase(redis_database);
    	jedisConnectionFactory.setHostName(redis_host);
    	if(redis_pass!=null){
    	   jedisConnectionFactory.setPassword(redis_pass);
    	}
    	jedisConnectionFactory.setPort(redis_port);
    	jedisConnectionFactory.setTimeout(redis_timeout);
    	jedisConnectionFactory.setUsePool(true);
    	JedisPoolConfig jedisPoolConfig=jedisConnectionFactory.getPoolConfig();
    	jedisPoolConfig.setMaxIdle(redis_pool_max_idle);
    	jedisPoolConfig.setMinIdle(redis_pool_min_idle);
        return jedisConnectionFactory;
    }
	
    @Bean
    public StringRedisTemplate dnaStringRedisTemplate(@Qualifier("redisConnectionFactory") RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
}
