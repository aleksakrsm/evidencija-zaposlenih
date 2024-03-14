/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.configuration.openapi;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author aleks
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Evidencija zaposlenih API",
                contact = @Contact(
                        name = "Aleksa Krsmanovic", email = "aleksa.krsma@gmail.com"
                ),
                description = "Web aplikacija za evidenciju zaposlenih na fakultetu"
        )
)
public class OpenAPIConfiguration {
    @Bean
public OpenAPI customizeOpenAPI() {
    final String securitySchemeName = "bearerAuth";
//    final String securitySchemeName = "basicAuth";
    return new OpenAPI()
      .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement()
        .addList(securitySchemeName))
      .components(new Components()
        .addSecuritySchemes(securitySchemeName, new io.swagger.v3.oas.models.security.SecurityScheme()
          .name(securitySchemeName)
          .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
          .scheme("bearer")
//          .scheme("basic")
          .bearerFormat("JWT")));
    }
}
