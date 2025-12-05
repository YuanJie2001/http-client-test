package mxr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient voiceprintClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:8000/voiceprint/api/v1/model")
                .defaultHeader("Authorization", "Bearer voiceprint-open-api-token")
                .build();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
