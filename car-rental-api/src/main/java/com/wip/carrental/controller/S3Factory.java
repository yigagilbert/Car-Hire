//package com.wip.carrental.controller;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.util.List;
//
//import java.util.UUID;
//
//import org.apache.commons.fileupload.FileItem;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.amazonaws.services.s3.AmazonS3Client;
//
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//import com.amazonaws.services.s3.model.S3Object;
//import com.amazonaws.AmazonClientException;
//import com.amazonaws.AmazonServiceException;
//
//
//@Service
//public class S3Factory {
//
//
//    @Autowired
//    AmazonS3Client amazonS3Client;
//
//
//    private static String bucketName     = "cars-rent";
//    private static String keyName  = "Object-"+UUID.randomUUID();
//    //   @Value("${amazonProperties.bucketName}")
//     final String defaultBucketName = "cars-rent";
//
//	//    @Value("${amazonProperties.endpointUrl}")
//	final  String defaultBaseFolder = "pics";
//
//
//
//
//
//
//     public String fileUploader(List<FileItem> fileData) throws IOException {
//    	 String result = "Upload unsuccessfull because ";
//
//    	// AmazonS3  s3 =(AmazonS3)amazonS3Client.getObject(defaultBucketName, defaultBaseFolder+"/"+keyName);
//
//             try {
//
//                 S3Object s3Object = new S3Object();
//
//                 ObjectMetadata omd = new ObjectMetadata();
//                 omd.setContentType(fileData.get(0).getContentType());
//                 omd.setContentLength(fileData.get(0).getSize());
//                 omd.setHeader("filename", fileData.get(0).getName());
//
//                 ByteArrayInputStream bis = new ByteArrayInputStream(fileData.get(0).get());
//                 s3Object.setObjectContent(bis);
//                  amazonS3Client.putObject(new PutObjectRequest(bucketName, keyName, bis, omd));
//                 s3Object.close();
//
//                 result = "Uploaded Successfully.";
//             } catch (AmazonServiceException ase) {
//                System.out.println("Caught an AmazonServiceException, which means your request made it to Amazon S3, but was "
//                     + "rejected with an error response for some reason.");
//
//                System.out.println("Error Message:    " + ase.getMessage());
//                System.out.println("HTTP Status Code: " + ase.getStatusCode());
//                System.out.println("AWS Error Code:   " + ase.getErrorCode());
//                System.out.println("Error Type:       " + ase.getErrorType());
//                System.out.println("Request ID:       " + ase.getRequestId());
//
//                result = result + ase.getMessage();
//             } catch (AmazonClientException ace) {
//                System.out.println("Caught an AmazonClientException, which means the client encountered an internal error while "
//                     + "trying to communicate with S3, such as not being able to access the network.");
//
//                result = result + ace.getMessage();
//              }catch (Exception e) {
//                  result = result + e.getMessage();
//            }
//
//             return result;
//
//     }
//
//}