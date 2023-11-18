package com.horacioing.ivstore.infrastructure.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;



@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "IVStore API",
                description = "API Rest de IVStore",
                version = "1.0.0",
                license = @License(
                        name = "Apache License, Version 2.0",
                        url = "https://opensource.org/license/apache-2-0/"
                ),
                contact = @Contact(
                        name = "Ing. Horacio",
                        url = "https://github.com/Lopez19",
                        email = "hola@horacioing.com"
                ),
                termsOfService = "https://github.com/Lopez19"
        ),
        externalDocs = @ExternalDocumentation(
                description = "External Wiki Documentation",
                url = "https://wikidoc.com"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Prod ENV",
                        url = "https://tcadac.stackhero-network.com"
                ),
        }
)
public class SwaggerConfig {
}
