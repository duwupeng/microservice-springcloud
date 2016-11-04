package pn.eric.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Eric
 * @date RestServer
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@EnableScheduling
@EnableAsync
public class RestServer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RestServer.class, args);
    }

    @Bean
    public CacheManager cacheManager(){
//        ConcurrentMapCacheManager personCacheManager = new ConcurrentMapCacheManager("persons");
        GuavaCacheManager personCacheManager = new GuavaCacheManager("persons");
        return personCacheManager;
    }
}
