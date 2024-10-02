package com.wip.carrental.controller;


import com.wip.carrental.repository.AdminsRepository;
import com.wip.carrental.controller.exceptions.ResourceNotFoundException;
import com.wip.carrental.model.Admin;


import io.swagger.annotations.Api;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "Admin Management System")
public class AdminsController {

    @Autowired
    private AdminsRepository adminsRepository;

    @GetMapping("/admins")
    public List<Admin> getAllDrivers() {
        return (List<Admin>) adminsRepository.findAll();
    }


    // sign up method for admin
    @PostMapping("/admins/signUp")
    public ResponseEntity<?> postAdmin(@RequestBody Admin adminObj) {

        try {
            adminObj.setAdminPassword(hashPassword(adminObj.getAdminPassword()));
            return ResponseEntity.ok(adminsRepository.save(adminObj));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body("email id already exists");
        }
    }

    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    @PostMapping("/admins/login")
    public ResponseEntity<?> loginAdmin(@RequestBody Admin requestObj) {
        Admin admin = adminsRepository.findById(requestObj.getAdminEmailId()).orElse(null);
        if (admin != null) {
            if (checkPass(requestObj.getAdminPassword(), admin.getAdminPassword())) {
                return ResponseEntity.ok(admin);
            }
            return ResponseEntity.status(403).eTag("password is not matching").build();
        }
        return ResponseEntity.notFound().eTag("admin not found").build();
    }
    private boolean checkPass(String plainPassword, String hashedPassword) {
        return (BCrypt.checkpw(plainPassword, hashedPassword));
    }

    @PutMapping("/admins/{adminEmailId}")
    public ResponseEntity<?> updateAdmin(@PathVariable String adminEmailId, @RequestBody Admin adminRequestBody) {
        return adminsRepository.findById(adminEmailId).map(admin -> {
            admin.setAdminAddress(adminRequestBody.getAdminAddress());
            admin.setAdminName(adminRequestBody.getAdminName());
            admin.setAdminPassword(hashPassword(adminRequestBody.getAdminPassword()));
            return ResponseEntity.ok(adminsRepository.save(admin));
        }).orElseThrow(() -> new ResourceNotFoundException("Email Id " + adminEmailId + " not found"));
    }
}
