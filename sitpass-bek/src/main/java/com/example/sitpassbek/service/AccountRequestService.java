package com.example.sitpassbek.service;


import com.example.sitpassbek.dto.accountRequest.AccountRequestDTO;
import com.example.sitpassbek.dto.accountRequest.CreateAccountRequestDTO;
import com.example.sitpassbek.dto.accountRequest.RejectRequestDTO;
import com.example.sitpassbek.dto.user.UserDTO;

import java.util.List;


public interface AccountRequestService {

    AccountRequestDTO createAccountRequest(CreateAccountRequestDTO dto);

    UserDTO approveRequest(Long requestId);

    AccountRequestDTO rejectRequest(RejectRequestDTO dto);

    List<AccountRequestDTO> getAllRequests();

}
