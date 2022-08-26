package com.food.food_app.configuration;

import java.util.Collections;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;



@Configuration
public class SwaggerConfig {
   
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).select().paths(PathSelectors.any()).build();
    }



   private ApiInfo getInfo() {
        return new ApiInfo("Food-App Application : Backend Application","Developed by Priyanshu Arora","1.0","Terms of service", new Contact("priyanshu", "http://foodApp.com", "priyanshu.clarivate@gmail.com"),"Licence of api", "Api url", Collections.emptyList());
    }
}
