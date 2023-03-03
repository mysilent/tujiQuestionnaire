package com.wang.tujiquestionnaire;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author wang
 */
@MapperScan
@EnableTransactionManagement
@EnableOpenApi
@SpringBootApplication

public class TujiQuestionnaireApplication {

    public static void main(String[] args) {
        SpringApplication.run(TujiQuestionnaireApplication.class, args);
    }

}
