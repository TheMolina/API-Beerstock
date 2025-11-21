package one.digitalinnovation.beerstock.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Beerstock API")
                        .version("1.0.0")
                        .contact(new Contact().name("Beerstock Maintainer").email("maintainer@example.com"))
                        .description("API de exemplo para gerenciamento de estoque de cervejas"));
    }
}
