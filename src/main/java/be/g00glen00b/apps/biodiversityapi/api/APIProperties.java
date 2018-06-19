package be.g00glen00b.apps.biodiversityapi.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "api")
@Data
public class APIProperties {
    /**
     * Path pattern of the location that contains all GBIF datasets
     */
    private String gbifLocationPattern = "classpath:/datasets/*/occurrence.txt";

    /**
     * Amount of items processed at once during a batch. Do not exceed database query limits.
     */
    private int chunkSize = 1000;

    /**
     * Endpoint URL of the Bing search API
     */
    private String imageSearchApi = "https://api.cognitive.microsoft.com/bing/v7.0/images/search";

    /**
     * Subscription key to the API gateway of Azure
     */
    private String subscriptionKey;
}
