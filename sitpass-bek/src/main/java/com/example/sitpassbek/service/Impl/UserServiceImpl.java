package com.example.sitpassbek.service.Impl;

import com.example.sitpassbek.dto.user.ChangePasswordDTO;
import com.example.sitpassbek.dto.user.CreateUserDTO;
import com.example.sitpassbek.dto.user.UserDTO;
import com.example.sitpassbek.mapper.UserMapper;
import com.example.sitpassbek.model.User;
import com.example.sitpassbek.repository.UserRepository;
import com.example.sitpassbek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,UserMapper userMapper,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO createUser(CreateUserDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        User user = new User();

        user.setAddress(dto.getAddress());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
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

    @Override
    public void changePassword(Long userId, ChangePasswordDTO passwordDTO) {

        User user = userRepository.findByIdAndIsDeletedFalse(userId).
                orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        if (!passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }

        if (!passwordDTO.getNewPassword().equals(passwordDTO.getConfirmPassword())) {
            throw new RuntimeException("New passwords do not match");
        }

        user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
        userRepository.save(user);
    }


}
