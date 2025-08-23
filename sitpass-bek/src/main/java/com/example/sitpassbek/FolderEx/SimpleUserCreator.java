//package com.example.sitpassbek.controller;
//
//
//import com.example.sitpassbek.model.Administrator;
//import com.example.sitpassbek.model.User;
//import com.example.sitpassbek.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//
//@Component
//public class SimpleUserCreator implements CommandLineRunner {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
////        User user = new User();
////        user.setEmail("test@example.com");
////        user.setPassword(passwordEncoder.encode("password123"));
////        user.setAddress("KKKKK");
////        user.setCreatedAt(LocalDate.now());
////
////
////        userRepository.save(user);
//
//
//        Administrator user = new Administrator();
//        user.setEmail("test123@example.com");
//        user.setPassword(passwordEncoder.encode("password123"));
//        user.setAddress("KKKKK");
//        user.setCreatedAt(LocalDate.now());
//
//
//        userRepository.save(user);
//
//
//
//    }
//}
