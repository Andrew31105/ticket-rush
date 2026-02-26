package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;

@Configuration
public class AppConfig {
    @Bean
    public DefaultRedisScript<Long> deductInventoryScript(){
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setLocation(new ClassPathResource("scripts/deduct_inventory.lua"));
        script.setResultType(Long.class);
        return script;
    }
}
