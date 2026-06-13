package riku.spring.wobble.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import riku.spring.wobble.dto.ExplainRequest;
import riku.spring.wobble.dto.ExplainResponse;
import riku.spring.wobble.service.GeminiService;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageController {


    private final GeminiService geminiService;

    @PostMapping("/extract")
    public ResponseEntity<?> extract(@RequestParam("file")MultipartFile file)
        throws Exception {
        return ResponseEntity.ok(geminiService.extract(file));
    }

    @PostMapping("/explain")
    public ResponseEntity<ExplainResponse> explain(
            @RequestBody ExplainRequest request) throws JsonProcessingException {

        return ResponseEntity.ok(
                geminiService.explain(
                        request.latex()
                )
        );
    }
}
