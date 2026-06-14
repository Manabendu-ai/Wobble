package riku.spring.wobble.dto;

import java.util.Map;

public record ExplainResponse(
        String title,
        String explanation,
        Map<String, String> variables
) {
}