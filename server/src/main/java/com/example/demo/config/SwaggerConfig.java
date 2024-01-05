package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.util.List;

@Profile("springdoc")
@Configuration
@OpenAPIDefinition(
        // 서버 설정 - 어노테이션
        // servers = {
        //         @Server(url = "http://localhost")
        // },
        info = @Info(
                title = "EXAMPLE Rest API 명세서",
                description = "API 명세서",
                version = "v1",
                contact = @Contact(
                        name = "terria1020",
                        email = "jaehan1346@gmail.com",
                        url = "https://github.com/terria1020"
                )
        )
)
public class SwaggerConfig {
    private final Environment env;

    public SwaggerConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public OpenAPI openAPI() {
        // SecuritySecheme명
        String jwtSchemeName = "jwt";
        // API 요청헤더에 인증정보 포함
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
        // SecuritySchemes 등록
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP) // HTTP 방식
                        .scheme("bearer")
                        .bearerFormat("JWT")); // 토큰 형식을 지정하는 임의의 문자(Optional)

        // 서버 설정 (구체적으로 수동 설정)
        io.swagger.v3.oas.models.servers.Server server = new io.swagger.v3.oas.models.servers.Server();
        server.description("server");
        server.url("http://localhost:" + env.getProperty("server.port"));

        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .servers(List.of(server))
                .components(components);
    }
}
