//package com.example.sitpassbek.FolderEx;
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
//
//        User user = new User();
//        user.setEmail("user2@gmail.com");
//        user.setPassword(passwordEncoder.encode("password123"));
//        user.setAddress("Bulevar Oslobodjenja 100");
//        user.setCreatedAt(LocalDate.now());
//
//
//        userRepository.save(user);
//
//        User user1 = new User();
//        user1.setEmail("user4@gmail.com");
//        user1.setPassword(passwordEncoder.encode("password123"));
//        user1.setAddress("Bulevar Oslobodjenja 100");
//        user1.setCreatedAt(LocalDate.now());
//
//
//        userRepository.save(user1);
//
//
//        Administrator user2 = new Administrator();
//        user2.setEmail("admin2@gmail.com");
//        user2.setPassword(passwordEncoder.encode("password123"));
//        user2.setAddress("Bulevar Oslobodjenja 100");
//        user2.setCreatedAt(LocalDate.now());
//
//
//        userRepository.save(user2);
//
//
//
//    }
//}
