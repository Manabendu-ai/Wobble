package riku.spring.wobble.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import riku.spring.wobble.service.GeminiService;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageController {


    private final GeminiService geminiService;

    @PostMapping("/extract")
    public ResponseEntity<String> extract(@RequestParam("file")MultipartFile file){
        return ResponseEntity.ok("Received: "+file.getOriginalFilename());
    }

}
