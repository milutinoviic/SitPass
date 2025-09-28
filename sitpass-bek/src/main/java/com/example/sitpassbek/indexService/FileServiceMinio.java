package com.example.sitpassbek.indexService;

import io.minio.GetObjectResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileServiceMinio {

    String store(MultipartFile file, String serverFilename);

    void delete(String serverFilename);

    GetObjectResponse loadAsResource(String serverFilename);

}
