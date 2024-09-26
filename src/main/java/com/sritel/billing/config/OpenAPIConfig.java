package com.sritel.billing.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI billingServiceAPI() {
        return new OpenAPI()
                .info(new Info().title("Billing Service API")
                        .description("This is the REST API for Billing Service")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("You can refer tot he Billing Service Wiki Documentation")
                        .url("https://billing-service-dummy-url.com/docs"));
    }
}
