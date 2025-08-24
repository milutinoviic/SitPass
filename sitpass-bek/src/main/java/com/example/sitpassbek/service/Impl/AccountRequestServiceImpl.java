package com.example.sitpassbek.service.Impl;

import com.example.sitpassbek.dto.accountRequest.AccountRequestDTO;
import com.example.sitpassbek.dto.accountRequest.CreateAccountRequestDTO;
import com.example.sitpassbek.dto.accountRequest.RejectRequestDTO;
import com.example.sitpassbek.dto.user.UserDTO;
import com.example.sitpassbek.enums.RequestStatus;
import com.example.sitpassbek.mapper.AccountRequestMapper;
import com.example.sitpassbek.model.AccountRequest;
import com.example.sitpassbek.model.User;
import com.example.sitpassbek.service.AccountRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sitpassbek.mapper.UserMapper;
import com.example.sitpassbek.repository.AccountRequestRepository;
import com.example.sitpassbek.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccountRequestServiceImpl implements AccountRequestService {

    private final AccountRequestRepository accountRequestRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AccountRequestMapper accountRequestMapper;


    @Autowired
    public AccountRequestServiceImpl(AccountRequestRepository accountRequestRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper,AccountRequestMapper accountRequestMapper) {
        this.accountRequestRepository = accountRequestRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.accountRequestMapper = accountRequestMapper;

    }

    @Override
    public AccountRequestDTO createAccountRequest(CreateAccountRequestDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail()) ||
                accountRequestRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        AccountRequest request = new AccountRequest();
        request.setEmail(dto.getEmail());
        request.setPassword(passwordEncoder.encode(dto.getPassword()));
        request.setAddress(dto.getAddress());
        request.setCreatedAt(LocalDate.now());
        request.setDeleted(false);
        request.setStatus(RequestStatus.PENDING);

        request = accountRequestRepository.save(request);
        return accountRequestMapper.toDto(request);
    }

    @Override
    public UserDTO approveRequest(Long requestId) {

        AccountRequest req = accountRequestRepository.findByIdAndIsDeletedFalse(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (req.getStatus() != RequestStatus.PENDING) {
            throw new IllegalStateException("Request already processed");
        }

        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        user.setAddress(req.getAddress());
        user.setCreatedAt(LocalDate.now());
        user.setDeleted(false);

        userRepository.save(user);

        req.setStatus(RequestStatus.ACCEPTED);
        accountRequestRepository.save(req);

        return userMapper.convertUserToUserDTO(user);
    }

    @Override
    public AccountRequestDTO rejectRequest(RejectRequestDTO dto) {
        AccountRequest req = accountRequestRepository.findById(dto.getRequestId())
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        if (req.getStatus() != RequestStatus.PENDING) {
            throw new IllegalStateException("Request already processed");
        }

        req.setStatus(RequestStatus.REJECTED);
        req.setRejectionReason(dto.getReason());

        req = accountRequestRepository.save(req);
        return accountRequestMapper.toDto(req);
    }

    @Override
    public List<AccountRequestDTO> getAllRequests() {

        List<AccountRequest> requests = accountRequestRepository.findAllByIsDeletedFalseAndStatus(RequestStatus.PENDING);
        return accountRequestMapper.toDtoList(requests);
    }



}
