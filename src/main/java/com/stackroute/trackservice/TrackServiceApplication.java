package com.stackroute.trackservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@PropertySource("classpath:application-dev.properties")
public class TrackServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrackServiceApplication.class, args);
    }

}
