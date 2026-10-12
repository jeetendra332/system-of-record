
package com.example.sor.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI systemOfRecordOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("System of Record API")
                        .description(
                                "REST APIs for employee and department management, " +
                                        "authentication, audit logs, and system monitoring."
                        )
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("System of Record Project")
                                .url("https://github.com/jeetendra332")));
    }
}
