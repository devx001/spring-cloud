/*package com.devx.userservice.config;

import com.devx.userservice.UserServiceApplication;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan("com.devx.userservice.*")
@EnableSwagger2
@SwaggerDefinition(tags = {@Tag(name = "Products MicroService", description = "Api to get details products")})
public class SwaggerConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer swaggerProperties() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName("Api")
                .globalResponseMessage(RequestMethod.GET, Collections.singletonList(
                        new ResponseMessageBuilder().code(503).message("Temporarily unavailable service").build()))
                .globalResponseMessage(RequestMethod.POST, Collections.singletonList(
                        new ResponseMessageBuilder().code(503).message("Temporarily unavailable service").build()))
                .apiInfo(metaData())
                .select()
                .apis(RequestHandlerSelectors.basePackage(UserServiceApplication.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo metaData() {
        return new ApiInfo("Products Microservices",
                "Api to get details products",
                "1.0.0", "© 2020  Personal Juan",
                new Contact("Juan", "", "juan@prueba.com"),
                "licenciaURL", "licenciaURL", Collections.emptyList());
    }

}
*/