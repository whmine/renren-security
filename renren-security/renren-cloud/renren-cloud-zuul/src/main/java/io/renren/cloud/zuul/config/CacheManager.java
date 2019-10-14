package io.renren.cloud.zuul.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CacheManager implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.debug("缓存管理-->缓存初始化:服务启动!");
        log.debug("缓存管理-->缓存初始化:初始化完成!");
    }
}
