package riku.spring.wobble.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;
import riku.spring.wobble.dto.ExplainResponse;
import riku.spring.wobble.dto.FormulaResponse;
import riku.spring.wobble.dto.GeminiResponse;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeminiService {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    @Value("${gemini.api.key}")
    private String apiKey;

    public FormulaResponse extract(MultipartFile file) throws Exception {

        String base64 = Base64.getEncoder().encodeToString(file.getBytes());

        Map<String, Object> request = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", """
                        You are an OCR and mathematical formula extraction system.

                        Analyze the uploaded image and:
                        1. Extract all visible text.
                        2. Extract all mathematical formulas, equations, and expressions.
                        3. Convert every mathematical expression to valid LaTeX.
                        4. Preserve symbols, subscripts, superscripts, fractions, and Greek letters exactly.
                        5. If no formulas are present, return an empty array.

                        IMPORTANT:
                        - Return ONLY valid JSON.
                        - Do NOT use markdown.
                        - Do NOT wrap the response inside ```json.
                        - Do NOT include explanations or additional text.

                        Response format:
                        {
                          "title": "",
                          "text": "",
                          "latex": []
                        }

                        If a formula corresponds to a known law, theorem, or concept,
                        populate the title and text fields.

                        "text" should contain a short one-line description.
                        """),
                                Map.of("inlineData", Map.of(
                                        "mimeType", file.getContentType(),
                                        "data", base64
                                ))
                        ))
                ),
                // Force Gemini to return JSON directly — same as explain()
                "generationConfig", Map.of(
                        "responseMimeType", "application/json"
                )
        );

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey;

        // Use GeminiResponse (same as explain) to unwrap candidates[0].content.parts[0].text
        GeminiResponse response = restClient.post()
                .uri(url)
                .body(request)
                .retrieve()
                .body(GeminiResponse.class);

        // Extract the JSON string from Gemini's wrapper, then parse into FormulaResponse
        String json = response.candidates()
                .getFirst()
                .content()
                .parts()
                .getFirst()
                .text();

        return objectMapper.readValue(json, FormulaResponse.class);
    }

    public ExplainResponse explain(String latex) throws JsonProcessingException {

        Map<String, Object> request = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", """
                        You are an expert mathematics tutor.

                        Explain the following LaTeX formula:

                        %s

                        Return ONLY valid JSON.

                        Response format:
                        {
                          "title": "",
                          "explanation": "",
                          "variables": {}
                        }

                        Rules:
                        - title should contain the name of the law, theorem, or concept.
                        - explanation should be concise and beginner-friendly.
                        - variables should map each symbol to its meaning.
                        - Return only JSON.
                        """.formatted(latex))
                        ))
                ),
                "generationConfig", Map.of(
                        "responseMimeType", "application/json"
                )
        );

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey;

        GeminiResponse response = restClient.post()
                .uri(url)
                .body(request)
                .retrieve()
                .body(GeminiResponse.class);

        String json = response.candidates()
                .getFirst()
                .content()
                .parts()
                .getFirst()
                .text();

        return objectMapper.readValue(json, ExplainResponse.class);
    }
}