package by.malinka.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info().title("Book Service")
                        .contact(new Contact().name("maxicids")
                                .email("maxicids@gmail.com")))
                .servers(List.of(new Server().url("http://localhost:8081")
                        .description("Dev service")));
    }
}
