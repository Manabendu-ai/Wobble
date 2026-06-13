package riku.spring.wobble.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class GeminiService {

    private final RestClient restClient;

    @Value("${gemini.api.key}")
    private String apiKey;

    public String extract(MultipartFile file)throws Exception{
        byte[] imageBytes = file.getBytes();
        return Base64.getEncoder().encodeToString(imageBytes).substring(0,100)+"...";
    }
}
