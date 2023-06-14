package com.mural.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))).info(new Info()
                        .title("Mural Escola")
                        .description("API Rest da aplicação de Mural de Escola para posts sobre eventos e acontecimentos na escola.")
                        .contact(new Contact()
                                .name("Desenvolvedor")
                                .email("alessandro547@outlook.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://mural.escola/api/licenca")));
    }
}
