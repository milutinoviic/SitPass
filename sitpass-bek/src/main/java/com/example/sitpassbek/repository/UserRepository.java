package com.example.sitpassbek.repository;

import com.example.sitpassbek.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
