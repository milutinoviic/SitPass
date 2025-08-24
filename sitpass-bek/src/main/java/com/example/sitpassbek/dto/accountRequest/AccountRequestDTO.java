package com.example.sitpassbek.dto.accountRequest;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountRequestDTO {
    private Long id;
    private String email;
    private String address;
    private String status;
    private String rejectionReason;
    private LocalDate createdAt;
}

