package mxr.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient voiceprintClient() {
        return RestClient.builder()
                .requestFactory(new JdkClientHttpRequestFactory(HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build()))
                .baseUrl("http://127.0.0.1:8000/voiceprint/api/v1/model")
                .defaultHeader("Authorization", "Bearer voiceprint-open-api-token")
                .build();
    }

//    @Bean
//    @Primary
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

    @Bean
    @Primary
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .requestFactory(SimpleClientHttpRequestFactory.class)
                .rootUri("http://127.0.0.1:8000/voiceprint/api/v1/model")
                .defaultHeader("Authorization", "Bearer voiceprint-open-api-token")
                .build();
    }
}
