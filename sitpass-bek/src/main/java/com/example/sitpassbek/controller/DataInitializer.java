//package com.example.sitpassbek.controller;
//
//import com.example.sitpassbek.model.Administrator;
//import com.example.sitpassbek.model.User;
//import com.example.sitpassbek.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import java.time.LocalDate;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    private final UserRepository userRepository;
//
//    public DataInitializer(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        User user = new User();
//        user.setName("Petar");
//        user.setSurname("Petrovic");
//        user.setEmail("user@example.com");
//        user.setPassword("password123");
//        user.setBirthday(LocalDate.of(1990, 5, 20));
//        user.setCreatedAt(LocalDate.now());
//        user.setPhoneNumber("0612345678");
//        user.setAddress("Ulica 1");
//        user.setCity("Beograd");
//        user.setZipCode("11000");
//        user.setDeleted(false);
//
//        Administrator admin = new Administrator();
//        admin.setName("Marko");
//        admin.setSurname("Markovic");
//        admin.setEmail("admin@example.com");
//        admin.setPassword("adminpass123");
//        admin.setBirthday(LocalDate.of(1985, 3, 15));
//        admin.setCreatedAt(LocalDate.now());
//        admin.setPhoneNumber("0698765432");
//        admin.setAddress("Ulica 2");
//        admin.setCity("Novi Sad");
//        admin.setZipCode("21000");
//        admin.setDeleted(false);
//
//
//        userRepository.save(user);
//        userRepository.save(admin);
//    }
//}
