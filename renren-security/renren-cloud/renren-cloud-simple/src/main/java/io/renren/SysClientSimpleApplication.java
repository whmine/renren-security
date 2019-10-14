package io.renren;

import com.spring4all.swagger.EnableSwagger2Doc;
import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EnableSwagger2Doc
@Api("AGENT")
public class SysClientSimpleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SysClientSimpleApplication.class, args);
    }
}
