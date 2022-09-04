package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class swaggerconfig {
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("demo2 的API文档")
                .description("非常方便的工具,spring boot利用swagger构建API文档")
                .version("1.0")
                .build();
    }
    @Bean
    public Docket createRestApi(){
        Docket md=   new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
                .paths(PathSelectors.regex("/restful/.*"))
                .build();
        return md;

    }
}
