package com.example.blogpessoal.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    OpenAPI springBlogPessoalOpenApi() {
        return new OpenAPI()
                .info(new Info().title("Projeto Blog Pessoal")
                        .description("Back-end de um sistema de blog pessoal,um projeto acadêmico - Generation Brasil")
                        .version("v0.0.1")
                        .license(new License().name("Generation Brasil")
                                .url("https://brazil.generation.org/"))
                        .contact(new Contact().name("Matheus Pereira da Silva")
                                .email("matheus.s280604@gmail.com")))
                .externalDocs(new ExternalDocumentation().description("Github")
                        .url("https://github.com/matheuxsx3"));
    }

    @Bean
    OpenApiCustomizer customizerGlobalHeaderOpenApiCustomizer() {

        return openApi -> {
            openApi.getPaths().values()
                    .forEach(pathItem -> pathItem.readOperations()
                            .forEach(operation -> {
                                ApiResponses apiResponses = operation.getResponses();

                                apiResponses.addApiResponse("200", new ApiResponse().description("Sucesso!"));
                                apiResponses.addApiResponse("201", new ApiResponse().description("Objeto persistido!"));
                                apiResponses.addApiResponse("204", new ApiResponse().description("Objeto excluido!"));
                                apiResponses.addApiResponse("400", new ApiResponse().description("Erro na requisição!"));
                                apiResponses.addApiResponse("401", new ApiResponse().description("Acesso não autorizado!"));
                                apiResponses.addApiResponse("403", new ApiResponse().description("Acesso Proibido!"));
                                apiResponses.addApiResponse("404", new ApiResponse().description("Objeto não encontrado!"));
                                apiResponses.addApiResponse("500", new ApiResponse().description("Erro na aplicação!"));
                            }));};
    }
    private ApiResponse apiResponse(String message){
        return new ApiResponse().description(message);
    }
}
