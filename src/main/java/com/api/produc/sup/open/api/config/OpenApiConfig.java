package com.api.produc.sup.open.api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@SecurityScheme( type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER, name = "openApiSecurity")
@OpenAPIDefinition(security = {@SecurityRequirement(name="")})
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenAPI(@Value("3.0") String appVersion) {
	    return new OpenAPI()
	      .info(new Info()
	      .title("ApiRest Productos Supermercado")
	      .version(appVersion)
	      .description("* Api Rest para el Microservice App_MicroFrontEnd_Productos implementada con Spring Boot, Spring MVC, Spring Security, JWT , Spring Data JPA, SpringDoc-OpenApi, Swagger UI, Maven, Lombok, Postman, Log4j, Git, DBeaver, pgAdmin y PostgreSQL.\r\n"
	      		+ "* La Api Rest implementa todas las Operaciones CRUD, tanto para productos como para usuarios. \r\n"
	      		+ "* Se separa la capa de seguridad para la autenticación , implementando Spring Security y JWT. Además de realizar las operaciones CRUD para usuarios se aplica login y signin para la capa de presentación.\r\n"
	      		+ "* También se desarrollan los métodos de búsquedas independientes de tipo Like para todos los campos, tanto de usuarios como productos.\r\n"
	      		+ "* Los objetos de tipo getBy se manipulan como paginados, salvo los getById y Optional que se requiere un response por objeto y no una E.D como de tipo lista, stream, etc.\r\n"
	      		+ "* Se Desarrollan Clases Específicas para el Manejo de Excepciones para cada Servicio , como también un manejador de excepciones y validaciones por campos de beans.\r\n"
	      		+ "* Todas las funcionalidades tienen generación de logs en el Server para los errores y excepciones personalizadas\r\n"
	      		+ "* Se incluye documentación completa de la Api con open-api para la visualización con swagger-ui, las anotaciones de open-api se aplican junto con los códigos de respuesta de tipo HTTP para cada función en los respectivos controllers.\r\n"
	      		+ "* Se pone a disposición todos los recursos anteriores para productos y usuarios.\r\n"
	      		+ "* Entre Otros.")
	      .termsOfService("http://swagger.io/terms/")
	      .license(new License().name("GNU v3.0")
	      .url("https://github.com/andresWeitzel/ApiRest_MicroFrontEnd_ProductosSupermercado/blob/master/LICENSE")))
	      ;
	    
	}
	
	


}
