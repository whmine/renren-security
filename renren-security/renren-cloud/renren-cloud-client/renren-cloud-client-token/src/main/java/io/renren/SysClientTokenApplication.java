package io.renren;

import com.spring4all.swagger.EnableSwagger2Doc;
import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EnableSwagger2Doc
@Api("TOKEN")
public class SysClientTokenApplication {
    public static void main(String[] args) {
        SpringApplication.run(SysClientTokenApplication.class, args);
    }
}
