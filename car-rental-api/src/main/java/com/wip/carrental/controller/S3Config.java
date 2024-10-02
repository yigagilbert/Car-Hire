//package com.wip.carrental.controller;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//
//@Configuration
//public class S3Config {
//
//   // @Value("${amazonProperties.accessKey}")
//
//   final String accessKey = "carefull";
//    //@Value("${amazonProperties.secretKey}")
//
//   final String secretKey = "carefull" ;
//
//
//
//    @Bean
//    public  AmazonS3Client amazonS3Client() {
//    BasicAWSCredentials creds = new BasicAWSCredentials(this.accessKey, this.secretKey);
//
//    AmazonS3Client  s3Client = (AmazonS3Client)AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds)).build();
//       return s3Client;
//    }
//
//}
