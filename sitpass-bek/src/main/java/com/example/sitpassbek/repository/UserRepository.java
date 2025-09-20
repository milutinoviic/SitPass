package com.example.sitpassbek.repository;

import com.example.sitpassbek.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByIdAndIsDeletedFalse(Long id);

    @Query("SELECT u FROM User u WHERE u.isDeleted = false AND u.id NOT IN (SELECT m.user.id FROM Manages m WHERE m.isDeleted = false)")
    List<User> findAllActiveUsersNotInManages();

    User findUserById(Long id);
}
