package com.wang.tujiquestionnaire.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * ClassName: Swagger2Config
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/2/28-11:44
 */
@EnableOpenApi
@Configuration
public class Swagger2Config {
        @Bean
        public Docket createRestApi() {
            // v2 不同
            return new Docket(DocumentationType.OAS_30)
                    .apiInfo(apiInfo())
                    .select()
                    // 设置扫描路径
                    .apis(RequestHandlerSelectors.basePackage("com.wang.tujiquestionnaire"))
                    .build();
        }
    /**
     * 该套 API 说明，包含作者、简介、版本、host、服务URL
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("api接口文档")
                .contact(new Contact("wang","locahost:3306","邮箱"))
                .version("0.0.1")
                .termsOfServiceUrl("locahost:3306/")
                .description("api文档")
                .build();

    }
}
