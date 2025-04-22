package com.tofek.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

@SpringBootApplication
public class UploadFileToS3Application {

	public static void main(String[] args){
		SpringApplication.run(UploadFileToS3Application.class, args);
	}

}
