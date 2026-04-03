package com.example.demo.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@ConditionalOnProperty(name = "springdoc.swagger-ui.enabled", havingValue = "true", matchIfMissing = true)
public class DocumentationConfig {
    private final BuildProperties buildProperties;

    public DocumentationConfig(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public String appVersion() {
        return "dev";
    }

    @Bean
    public OpenAPI api() {
        return new OpenAPI().info(info());
    }

    private Info info() {
        return new Info()
                .title("TITLE")
                .description("DESCRIPTION")
                .version(buildProperties.getVersion());
    }

    @Bean
    public OpenApiCustomizer sortTagsInOrder(List<String> tagsOrder) {
        // Define tag order here:

        return openApi -> {
            if (openApi.getTags() != null) {
                // Convert to map for quick lookup
                Map<String, Tag> tagMap = new LinkedHashMap<>();
                for (Tag tag : openApi.getTags()) {
                    tagMap.put(tag.getName(), tag);
                }

                openApi.setTags(
                        tagsOrder.stream()
                                .map(tagMap::get) // pick tag by name
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList())
                );
            }
        };
    }
}
