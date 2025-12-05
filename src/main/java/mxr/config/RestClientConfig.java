package mxr.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
    @Primary
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public RestTemplate restTemplateBuilder(RestTemplateBuilder builder) {
//        return builder
//                .rootUri("http://localhost:8000/voiceprint/api/v1/model")
//                .defaultHeader("Authorization", "Bearer voiceprint-open-api-token")
//                .build();
//    }
}
