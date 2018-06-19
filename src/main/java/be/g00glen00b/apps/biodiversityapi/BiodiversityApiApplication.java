package be.g00glen00b.apps.biodiversityapi;

import be.g00glen00b.apps.biodiversityapi.api.APIProperties;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableBatchProcessing
@EnableCaching
@EnableSwagger2
@EnableConfigurationProperties(APIProperties.class)
public class BiodiversityApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiodiversityApiApplication.class, args);
    }

    @Bean
    public GeometryFactory geometryFactory() {
        return new GeometryFactory();
    }

    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(messageSource);
        return validatorFactoryBean;
    }
}
