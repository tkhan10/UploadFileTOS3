package com.tofek.aws.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.core.SdkRequest;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;

import java.io.File;


@RestController
@RequestMapping("/")
public class CreateBucketController {
    @Autowired
    S3Client s3Client;

    @GetMapping("createBucket")
    public void create(){
        String bucketName = "tofek-bucket";
        s3Client.createBucket(request -> request.bucket(bucketName));
    }

    @GetMapping("deleteBucket")
    public void getBuckets(){
        String bucketName = "tofek-bucket";
        s3Client.deleteBucket(request -> request.bucket(bucketName));
    }

    @GetMapping("addData")
    public void addObject(){
        try {
            String bucketName = "tofek-bucket";
            String key = "I94_Saman_2.pdf";
            File file = new File("/Users/tofekkhan/JavaProjectSpring/sourcefolder/I94_Saman_2.pdf");
            PutObjectRequest request = PutObjectRequest.builder().bucket(bucketName).key(key).build();
            s3Client.putObject(request, RequestBody.fromFile(file));
            System.out.println("-------File uploaded successfully-------");
        }catch (Exception e){
            System.out.println("-------File upload failed--------");
        }
    }
}
