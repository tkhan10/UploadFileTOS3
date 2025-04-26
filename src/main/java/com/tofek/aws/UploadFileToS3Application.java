package com.tofek.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

@SpringBootApplication
public class UploadFileToS3Application {

	public static void main(String[] args){
		SpringApplication.run(UploadFileToS3Application.class, args);
	}

	@Bean("S3Client")
	public S3Client makeConnection(){
		String accessKey = "AKIAUYBR4NCZDLTKIWMH";
		String secretKey = "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";
		String regionName = "us-east-2";
		AwsCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

		S3Client s3Client = S3Client.builder()
				.region(Region.of(regionName))
				.credentialsProvider(StaticCredentialsProvider.create(credentials))
				.build();

		return s3Client;
	}

}
