package br.com.fiap.netflix.like.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("br.com.fiap.netflix.like.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo() {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("Api de Envio de Menssagem de LIKE ");
       //apiInfoBuilder.description("API para cadastro de usuarios");
        apiInfoBuilder.version("1.0");
        //apiInfoBuilder.termsOfServiceUrl("Termo de uso: em desenvolvimento");
        //apiInfoBuilder.license("Licença - Open Source");
        //apiInfoBuilder.licenseUrl("Termo de uso: em desenvolvimento");
        apiInfoBuilder.contact(new Contact("GRUPO1AOJ","GRUPO1AOJ","GRUPO1AOJ"));

        return apiInfoBuilder.build();

    }



}
