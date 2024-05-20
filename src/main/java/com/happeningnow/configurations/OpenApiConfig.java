package com.happeningnow.configurations;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi apiVersion1() {
        return GroupedOpenApi.builder()
                .group("Happening now API V1")
                .pathsToMatch("/**")
                .build();
    }
}
