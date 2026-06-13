package riku.spring.wobble.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeminiService {

    private final RestClient restClient;

    @Value("${gemini.api.key}")
    private String apiKey;

    public String extract(MultipartFile file)throws Exception{

        String base64 =
                Base64.getEncoder()
                        .encodeToString(
                                file.getBytes()
                        );

        Map<String, Object> request =
                Map.of(
                        "contents",
                        List.of(
                                Map.of(
                                        "parts",
                                        List.of(
                                                Map.of(
                                                        "text",
                                                        """
                                                        Extract all text and mathematical formulas
                                                        from this image.
                                                        Return formulas in LaTeX.
                                                        """
                                                ),
                                                Map.of(
                                                        "inlineData",
                                                        Map.of(
                                                                "mimeType",
                                                                file.getContentType(),
                                                                "data",
                                                                base64
                                                        )
                                                )
                                        )
                                )
                        )
                );

        String url =
                "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="
                        + apiKey;

        return restClient.post()
                .uri(url)
                .body(request)
                .retrieve()
                .body(String.class);
    }
}

