package jason.app.crawler.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class CamelConfig {

	@Bean
	  public RedisSerializer stringSerializer() {
	    return new StringRedisSerializer();
	  }
}
