package com.example.messagingapp.file;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.io.File.separator;
import static java.lang.System.currentTimeMillis;



import jakarta.annotation.Nonnull;

import java.nio.file.Paths;


@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {


    /*@Value("${application.file.uploads.media-output-path}")
    private String fileUploadPath;
    public String saveFile(
            @NonNull MultipartFile sourceFile,
            @NonNull String userId) {

        final String fileUploadSubPath = "users" + separator + userId ;
        return uploadFile(sourceFile , fileUploadSubPath);

    }

    private String uploadFile(
            @NonNull MultipartFile sourceFile,
            @NonNull String fileUploadSubPath) {
        final String finalUploadPath = fileUploadPath + separator + fileUploadSubPath;
        File targetFolder = new File(finalUploadPath);
        if(!targetFolder.exists()){
            boolean folderCreated = targetFolder.mkdirs();
            if(!folderCreated){
                log.error("Failed to create the target folder {}",targetFolder);
                return null;
            }
        }
        final String fileExtention = getFinalExtension(sourceFile.getOriginalFilename());
        String targetFilePath = finalUploadPath + separator + currentTimeMillis() + fileExtention;
        Path targetPath = Path.of(targetFilePath);
        try {
            Files.write(targetPath , sourceFile.getBytes());
            log.info("File saved to {}",targetPath);
            return targetFilePath;
        } catch (IOException e) {
            log.error("File was not saved ,",e);
        }
        return null;
    }

    private String getFinalExtension(String filename) {
        if(filename == null || filename.isEmpty()){
            return "";
        }
        int lastDotIndex = filename.lastIndexOf('.');
        if(lastDotIndex ==-1){
            return "";
        }
        return filename.substring(lastDotIndex+1).toLowerCase();
    }*/


    @Value("${application.file.uploads.media-output-path}")
    private String fileUploadPath;

    public String saveFile(
            @Nonnull MultipartFile sourceFile,
            @Nonnull String userId
    ) {
        final String fileUploadSubPath = "users" + separator + userId;
        return uploadFile(sourceFile, fileUploadSubPath);
    }

    private String uploadFile(
            @Nonnull MultipartFile sourceFile,
            @Nonnull String fileUploadSubPath
    ) {
        final String finalUploadPath = fileUploadPath + separator + fileUploadSubPath;
        File targetFolder = new File(finalUploadPath);

        if (!targetFolder.exists()) {
            boolean folderCreated = targetFolder.mkdirs();
            if (!folderCreated) {
                log.warn("Failed to create the target folder: " + targetFolder);
                return null;
            }
        }
        final String fileExtension = getFileExtension(sourceFile.getOriginalFilename());
        String targetFilePath = finalUploadPath + separator + currentTimeMillis() + "." + fileExtension;
        Path targetPath = Paths.get(targetFilePath);
        try {
            Files.write(targetPath, sourceFile.getBytes());
            log.info("File saved to: " + targetFilePath);
            return targetFilePath;
        } catch (IOException e) {
            log.error("File was not saved", e);
        }
        return null;
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return "";
        }
        return fileName.substring(lastDotIndex + 1).toLowerCase();
    }
}
