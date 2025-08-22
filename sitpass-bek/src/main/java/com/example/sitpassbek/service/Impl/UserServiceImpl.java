package com.example.sitpassbek.service.Impl;

import com.example.sitpassbek.dto.userDTO.CreateUserDTO;
import com.example.sitpassbek.dto.userDTO.UserDTO;
import com.example.sitpassbek.mapper.UserMapper;
import com.example.sitpassbek.model.User;
import com.example.sitpassbek.repository.UserRepository;
import com.example.sitpassbek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createUser(CreateUserDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        User user = new User();

        user.setAddress(dto.getAddress());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setCreatedAt(LocalDate.now());
        user.setDeleted(false);

        userRepository.save(user);

        return userMapper.convertUserToUserDTO(user);

    }

    @Override
    public UserDTO getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        return userMapper.convertUserToUserDTO(user);


    }


}
