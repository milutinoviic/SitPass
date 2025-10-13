package com.example.sitpassbek.service.Impl;

import com.example.sitpassbek.service.FileService;
import io.minio.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Objects;

@Service
public class FileServiceImpl implements FileService {

    private final MinioClient minioClient;

    @Autowired
    public FileServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Value("${spring.minio.bucket}")
    private String bucketName;

    @Override
    public String store(MultipartFile file, String serverFilename) {
        if (file.isEmpty()) {
            throw new RuntimeException("Failed to store empty file.");
        }

        var originalFilenameTokens =
                Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        var extension = originalFilenameTokens[originalFilenameTokens.length - 1];


        String baseName = serverFilename.replaceAll("\\.[^.]+$", "");
        String finalFilename = baseName + "." + extension;

        try {
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(finalFilename)
                    .headers(Collections.singletonMap("Content-Disposition",
                            "attachment; filename=\"" + file.getOriginalFilename() + "\""))
                    .stream(file.getInputStream(), file.getInputStream().available(), -1)
                    .build();
            minioClient.putObject(args);
        } catch (Exception e) {
            throw new RuntimeException("Error while storing file in Minio.");
        }

        return finalFilename;
    }


    @Override
    public void delete(String serverFilename) {
        try {
            RemoveObjectArgs args = RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(serverFilename)
                    .build();
            minioClient.removeObject(args);
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting " + serverFilename + " from Minio.");
        }
    }

    @Override
    public GetObjectResponse loadAsResource(String serverFilename) throws FileNotFoundException {
        try {

            var argsDownload = GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucketName)
                    .object(serverFilename)
                    .expiry(60 * 5)
                    .build();
            var downloadUrl = minioClient.getPresignedObjectUrl(argsDownload);
            System.out.println(downloadUrl);

            var args = GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(serverFilename)
                    .build();
            return minioClient.getObject(args);
        } catch (Exception e) {
            throw new FileNotFoundException("Document " + serverFilename + " does not exist.");
        }
    }
}
