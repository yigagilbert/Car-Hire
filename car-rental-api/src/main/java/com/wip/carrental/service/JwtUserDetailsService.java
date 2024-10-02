//package com.wip.carrental.service;
//
//
//import com.wip.carrental.model.Admin;
//import com.wip.carrental.model.Driver;
//import com.wip.carrental.repository.AdminsRepository;
//import com.wip.carrental.repository.DriverRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//
//@Component
//public class JwtUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    DriverRepository driverRepository;
//
//    @Autowired
//    AdminsRepository adminsRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String emailid) throws UsernameNotFoundException {
//
//        Driver driver = driverRepository.findById(emailid).orElse(null);
//        Admin admin = adminsRepository.findById(emailid).orElse(null);
//        if (driver != null) {
//            return new User(driver.getDriverEmailId(), driver.getDriverPassword(),
//                    new ArrayList<>());
//        } else if (admin != null) {
//            return new User(admin.getAdminEmailId(), admin.getAdminPassword(),
//                    new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + emailid);
//        }
//    }
//
//}
