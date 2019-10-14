package io.renren.cloud.zuul;


import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableEurekaClient
@SpringBootApplication
@EnableZuulProxy
@EnableSwagger2Doc
public class ZuulSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulSpringApplication.class, args);
    }



}
