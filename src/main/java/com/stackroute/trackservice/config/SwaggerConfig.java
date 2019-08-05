package com.stackroute.trackservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //this annotation allows us to use annotations for dependency injection.
@EnableSwagger2 //annotation is used to enable the Swagger2 for your Spring Boot application.
public class SwaggerConfig
{
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select() //it returns an instance of ApiSelectorBuilderand it provides a way to control the endpoints exposed by swagger2
                .apis(RequestHandlerSelectors.any())//any() for both will make doccumentation for entire api available through swagger2
                .paths(PathSelectors.any())
                .build();
    }
    }
