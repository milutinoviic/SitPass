package com.example.sitpassbek.controller;


import com.example.sitpassbek.dto.accountRequest.AccountRequestDTO;
import com.example.sitpassbek.dto.accountRequest.CreateAccountRequestDTO;
import com.example.sitpassbek.dto.accountRequest.RejectRequestDTO;
import com.example.sitpassbek.dto.user.UserDTO;
import com.example.sitpassbek.service.AccountRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account-requests")
public class AccountRequestController {

    private final AccountRequestService accountRequestService;

    @Autowired
    public AccountRequestController(AccountRequestService accountRequestService) {
        this.accountRequestService = accountRequestService;
    }

    @PostMapping("/createAccountRequest")
    public ResponseEntity<AccountRequestDTO> createAccountRequest(@RequestBody CreateAccountRequestDTO dto) {
        AccountRequestDTO created = accountRequestService.createAccountRequest(dto);
        return ResponseEntity.ok(created);
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<UserDTO> approveRequest(@PathVariable Long id) {
        UserDTO user = accountRequestService.approveRequest(id);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/reject")
    public ResponseEntity<AccountRequestDTO> rejectRequest(@RequestBody RejectRequestDTO dto) {
        AccountRequestDTO rejected = accountRequestService.rejectRequest(dto);
        return ResponseEntity.ok(rejected);
    }

    @GetMapping("/getAllAccountRequest")
    public ResponseEntity<List<AccountRequestDTO>> getAllRequests() {
        return ResponseEntity.ok(accountRequestService.getAllRequests());
    }
}
