package com.example.sitpassbek.service;

import io.minio.GetObjectResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface FileService {

    String store(MultipartFile file, String serverFilename);

    void delete(String serverFilename);

    GetObjectResponse loadAsResource(String serverFilename) throws FileNotFoundException;

}
