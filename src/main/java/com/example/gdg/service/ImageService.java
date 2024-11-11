package com.example.gdg.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.gdg.config.S3Config;
import jakarta.validation.executable.ValidateOnExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final AmazonS3 amazonS3;
    private final S3Config s3Config;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    public String uploadImage(MultipartFile file) {
        String fileName = s3Config.getImagePath()+"/"+ file.getOriginalFilename();
        ObjectMetadata metadata= new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            amazonS3.putObject(bucket, fileName, file.getInputStream(), metadata);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    public void deleteImage(String fileName) {
        fileName = s3Config.getImagePath()+"/"+fileName;
        amazonS3.deleteObject(bucket, fileName);
    }

    public byte[] downloadImage(String fileName) {
        return null;
    }


}
