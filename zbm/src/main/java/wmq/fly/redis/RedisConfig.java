package wmq.fly.redis;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching //开启redis
@PropertySource(value = "classpath:/config/redis.properties")
public class RedisConfig extends CachingConfigurerSupport{
	//redis ip地址
    @Value("${spring.redis.host}")
    private String host;
    //Redis 端口号
    @Value("${spring.redis.port}")
    private int port;
    //超时时间
    @Value("${spring.redis.timeout}")
    private int timeout;
    //数据库索引（默认为0）
    @Value("${spring.redis.database}")
    private int database;
    
    /*
     * redis 连接池配置
     */
    //连接池最大连接数
    @Value("${spring.redis.pool.max-active}")
    private int maxActive;
    //连接池最大阻塞等待时间
    @Value("${spring.redis.pool.max-active}")
    private Long maxWaitMillis;
    //连接池最大空闲连接
    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;
    //连接池最小空闲连接
    @Value("${spring.redis.pool.min-idle}")
    private int minIdle;
    
    /**
       * 缓存键生成器。用于根据给定的方法创建键
     *(用作上下文)及其参数
     * 
     */
    @Bean(name="key")
    public KeyGenerator wiselyKeyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
    	//Redis连接池配置
    	JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        //配置redis连接信息
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setDatabase(database);
        factory.setPoolConfig(poolConfig);
        factory.setTimeout(timeout); //设置连接超时时间
        return factory;
    }
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        // Number of seconds before expiration. Defaults to unlimited (0)
        cacheManager.setDefaultExpiration(10); //设置key-value超时时间
        return cacheManager;
    }
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        //设置序列化工具，这样ReportBean不需要实现Serializable接口
        setSerializer(template); 
        template.afterPropertiesSet();
       // System.out.println("Bean  redisTemplate");
        return template;
    }
    //设置序列化工具，这样ReportBean不需要实现Serializable接口
    private void setSerializer(StringRedisTemplate template) {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
    }


}