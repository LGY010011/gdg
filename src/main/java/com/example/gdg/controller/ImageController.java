package com.example.gdg.controller;


import com.example.gdg.ApiResponse;
import com.example.gdg.apiPayload.status.SuccessStatus;
import com.example.gdg.service.ImageService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ApiResponse<String> uploadFile(@RequestPart(value = "file", required = false) MultipartFile file) {
        String fileUrl = imageService.uploadImage(file);

        return ApiResponse.onSuccess(fileUrl);
    }

    @DeleteMapping("/delete")
    public ApiResponse deleteFile(@RequestParam String fileName) {
        imageService.deleteImage(fileName);

        return ApiResponse.onSuccess(SuccessStatus._OK.getMessage());
    }

}
