package com.weather.PolyCube.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun restAPI() :Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.weather.PolyCube"))
            .paths(PathSelectors.any())
            .build()
    }

    private fun apiInfo() : ApiInfo {
        return ApiInfoBuilder()
            .title("Weather Rest API")
            .version("1.0.0")
            .description("open Weather API를 연동한 api")
            .build()
    }
}