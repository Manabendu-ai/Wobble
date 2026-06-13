package riku.spring.wobble.dto;

import java.util.List;

public record FormulaResponse(
        String title,
        String text,
        List<String> latex
) {}