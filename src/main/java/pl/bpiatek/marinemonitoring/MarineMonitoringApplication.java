package pl.bpiatek.marinemonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@EnableScheduling
@EnableFeignClients
@EnableCaching
@SpringBootApplication
public class MarineMonitoringApplication {

  public static void main(String[] args) {
    SpringApplication.run(MarineMonitoringApplication.class, args);
  }

  @Bean
  public CacheManager cacheManager() {
    SimpleCacheManager cacheManager = new SimpleCacheManager();
    Cache postsCache = new ConcurrentMapCache("token");
    cacheManager.setCaches(Arrays.asList(postsCache));
    return cacheManager;
  }

}
