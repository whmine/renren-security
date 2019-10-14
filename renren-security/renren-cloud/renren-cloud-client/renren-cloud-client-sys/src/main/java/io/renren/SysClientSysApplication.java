package io.renren;

import com.spring4all.swagger.EnableSwagger2Doc;
import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EnableSwagger2Doc
@Api("SYS")
public class SysClientSysApplication {
    public static void main(String[] args) {
        SpringApplication.run(SysClientSysApplication.class, args);
    }
}
