package io.renren.config;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CacheManager implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public void run(String... args) throws Exception {
        log.debug("缓存管理-->缓存初始化:服务启动!");
        log.debug("缓存管理-->缓存初始化:初始化用户信息!");


        log.debug("缓存管理-->缓存初始化:初始化完成!");
    }
}
