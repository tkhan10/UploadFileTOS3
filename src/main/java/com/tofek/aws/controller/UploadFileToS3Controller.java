package com.tofek.aws.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/upload")
public class UploadFileToS3Controller {

    @GetMapping("/")
    public Boolean uploadFile() {
        AtomicBoolean success = new AtomicBoolean(false);
        Path source = Paths.get("/Users/tofekkhan/JavaProjectSpring/sourcefolder");
        Path destination = Paths.get("/Users/tofekkhan/JavaProjectSpring/destinationfolder");

        //01-010-010+review.pdf
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(source)){
            stream
            .forEach(sourcePath -> {
                if(!sourcePath.endsWith(".DS_Store")) {
                    Path targetPath = destination.resolve(sourcePath.getFileName());
                    try {
                        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Copied: " + sourcePath + " to " + targetPath);
                        success.set(true);
                    } catch (IOException e) {
                        System.err.println("Failed to copy " + sourcePath + ": " + e.getMessage());
                    }
                }
            });
        }catch (IOException e) {
            System.err.println("Error reading source directory: " + e.getMessage());
        }
        return success.get();
    }
}