package be.g00glen00b.apps.biodiversityapi.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class APIConfiguration {

    public static final String GROUP_NAME = "biodiversity-api";

    @Bean
    public Docket docket(ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName(GROUP_NAME)
            .useDefaultResponseMessages(false)
            .apiInfo(apiInfo)
            .select().paths(regex("/api/.*"))
            .build();
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Biodiversity API")
            .description("API for fetching biodiversity related information about the environment")
            .version("1.0.1")
            .build();
    }

    @Bean
    public UiConfiguration uiConfiguration() {
        return UiConfigurationBuilder.builder()
            .deepLinking(true)
            .validatorUrl(null)
            .build();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
