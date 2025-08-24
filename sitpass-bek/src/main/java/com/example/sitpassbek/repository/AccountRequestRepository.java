package com.example.sitpassbek.repository;

import com.example.sitpassbek.enums.RequestStatus;
import com.example.sitpassbek.model.AccountRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRequestRepository extends JpaRepository<AccountRequest, Long> {

    boolean existsByEmail(String email);

    Optional<AccountRequest> findByIdAndIsDeletedFalse(Long id);

    List<AccountRequest> findAllByIsDeletedFalseAndStatus(RequestStatus status);

}

