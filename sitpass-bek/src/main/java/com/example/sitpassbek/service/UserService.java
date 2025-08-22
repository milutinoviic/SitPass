package com.example.sitpassbek.service;

import com.example.sitpassbek.dto.userDTO.CreateUserDTO;
import com.example.sitpassbek.dto.userDTO.UpdateUserDTO;
import com.example.sitpassbek.dto.userDTO.UserDTO;

public interface UserService {

    UserDTO createUser(CreateUserDTO dto);

    UserDTO getUserById(Long id);

}
