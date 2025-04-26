package com.tofek.aws.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;


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
        String bucketName = "tofek-bucket";
    }
}
