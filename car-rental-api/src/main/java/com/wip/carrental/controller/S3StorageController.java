//package com.wip.carrental.controller;
//
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.model.Bucket;
//import com.wip.carrental.controller.S3StorageController;
//
//@RestController
//@RequestMapping("/api/bucket")
//public class S3StorageController extends HttpServlet {
//
//	@Autowired
//	S3Factory s3;
//
//	 @Autowired
//	  AmazonS3Client amazonS3Client;
//
//
//
//	 @GetMapping(path = "/getbuckets")
//	    public List<Bucket> listBuckets(){
//	        return amazonS3Client.listBuckets();
//	    }
//
//
//
//	/**
//
//	@GetMapping(path = "/findupload")
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doPost(request, response);
//    }
//
//
//    @PostMapping(path = "/upload")
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//
//        try{
//            List<FileItem> multipartfiledata = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
//
//            //upload to S3
//            String result = s3.fileUploader(multipartfiledata);
//
//            out.print(result);
//        } catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//    */
//}
