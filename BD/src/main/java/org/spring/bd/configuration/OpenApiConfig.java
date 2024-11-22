package org.spring.bd.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "BD API Documentation",
                description = "API responsible for interaction with Mongo DB and PostgreSQL DB\", version = \"1.0.0\"",
                contact = @Contact(
                        name = "Vanya Bagay",
                        email = "voewoda88@gmail.com"
                )
        )
)

@Configuration
public class OpenApiConfig {
    @Bean
    public GroupedOpenApi mongoApi() {
        return GroupedOpenApi.builder()
                .group("mongo")
                .pathsToMatch("/mongo/**")
                .build();
    }

    @Bean
    public GroupedOpenApi postgresApi() {
        return GroupedOpenApi.builder()
                .group("postgres")
                .pathsToMatch("/postgres/**")
                .build();
    }

    @Bean
    public GroupedOpenApi converter() {
        return GroupedOpenApi.builder()
                .group("converter")
                .pathsToMatch("/converter/**")
                .build();
    }
}
