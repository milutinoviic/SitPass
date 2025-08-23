package com.example.sitpassbek.service;

import com.example.sitpassbek.dto.user.CreateUserDTO;
import com.example.sitpassbek.dto.user.UserDTO;

public interface UserService {

    UserDTO createUser(CreateUserDTO dto);

    UserDTO getUserById(Long id);

}
