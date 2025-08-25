//Add top-level API metadata (optional but recommended)
// com.example.config.OpenApiConfig
package com.example.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Demo Backend API",
                description = "Spring Boot 3 sample API with JWT, paging, Feign, etc.",
                version = "v1",
                contact = @Contact(name = "Team Backend", email = "backend@example.com"),
                license = @License(name = "Apache-2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")
        ),
        servers = {
                @Server(url = "http://localhost:8081", description = "Local Dev")
        }
)
@Configuration
public class OpenApiConfig { }
