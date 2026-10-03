package com.KambaFlix.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    public OpenAPI openAPI(){

        Contact contact = new Contact();
        contact.name("Dulcio Bernardo");
        contact.email("dulciobernardo77@gmail.com");

        Info info = new Info();
        info.title("KambaFlix");
        info.description("Plataforma moderna para gerir, organizar e consultar um catálogo de filmes de forma simples, rápida e intuitiva.");
        info.version("v1");
        info.contact(contact);

        return new OpenAPI()
                .info(info)
                .components(new Components().addSecuritySchemes(
                        "bearerAuth",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                ));
    }

}
