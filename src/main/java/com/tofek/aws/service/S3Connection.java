package com.tofek.aws.service;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.File;
import java.nio.file.Paths;

public class S3Connection {

    public static void main(String[] args) {
        String accessKey = "<YOUR_ACCESS_KEY>";
        String secretKey = "<YOUR_SECRET_KEY>";
        Region region = Region.US_EAST_1; // Change to your desired region

        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);

        try (S3Client s3 = S3Client.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build()) {

            // List all buckets
            ListBucketsResponse bucketsResponse = s3.listBuckets();
            bucketsResponse.buckets().forEach(bucket -> System.out.println(bucket.name()));

            // Upload a file
            String bucketName = "<YOUR_BUCKET_NAME>";
            String key = "example.txt";
            File file = new File("<PATH_TO_YOUR_FILE>");

            s3.putObject(PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(key)
                            .build(),
                    Paths.get(file.getAbsolutePath()));

            System.out.println("File uploaded successfully to bucket " + bucketName);

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}