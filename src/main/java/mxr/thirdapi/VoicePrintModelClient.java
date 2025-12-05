package mxr.thirdapi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test")
@Slf4j
@RequiredArgsConstructor
public class VoicePrintModelClient {

    private final RestClient voiceprintClient;

    private final RestTemplate restTemplate;

    private final RestTemplate restTemplateBuilder;

    @GetMapping("/register")
    public String register(String userid) {
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();

        multiValueMap.add("user_id", userid);
        multiValueMap.add("audio_data", new FileSystemResource("profile/c_3.wav"));
        String body = voiceprintClient
                .post()
                .uri("/register")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(multiValueMap)
                .retrieve()
                .body(String.class);
        return body;
    }

    /**
     * Only it is acceptable.
     * @param userid
     * @return
     */
    @GetMapping("/register2")
    public String register2(String userid){
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("user_id", userid);
        multiValueMap.add("audio_data", new FileSystemResource("profile/c_3.wav"));

//        MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
//        multipartBodyBuilder.part("user_id", userid);
//        multipartBodyBuilder.part("audio_data", new FileSystemResource("profile/c_3.wav"));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        httpHeaders.add("Authorization", "Bearer voiceprint-open-api-token");

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(multiValueMap, httpHeaders);
        return restTemplate.postForObject("http://127.0.0.1:8000/voiceprint/api/v1/model/register", httpEntity, String.class);
    }


    @GetMapping("/register3")
    public String register3(String userid){
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("user_id", userid);
        multiValueMap.add("audio_data", new FileSystemResource("profile/c_3.wav"));

//        MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();
//        multipartBodyBuilder.part("user_id", userid);
//        multipartBodyBuilder.part("audio_data", new FileSystemResource("profile/c_3.wav"));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(multiValueMap, httpHeaders);
        return restTemplateBuilder.postForObject("/register", httpEntity, String.class);
    }
}
