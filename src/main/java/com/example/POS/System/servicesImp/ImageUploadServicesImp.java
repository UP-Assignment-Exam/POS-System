package com.example.POS.System.servicesImp;

import com.example.POS.System.services.ImageUploadService;
import com.example.POS.System.upload.LocalImageUploader;
import com.example.POS.System.upload.S3ImageUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageUploadServicesImp implements ImageUploadService {
    @Value("${image.storage.type}")
    private String storageType;

    @Autowired
    private LocalImageUploader localUploader;

    @Autowired
    private S3ImageUploader s3Uploader;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        if ("s3".equalsIgnoreCase(storageType)) {
            return s3Uploader.upload(file);
        } else {
            return localUploader.upload(file);
        }
    }
}
