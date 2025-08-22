package com.example.sitpassbek.mapper;

import com.example.sitpassbek.dto.userDTO.UserDTO;
import com.example.sitpassbek.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO convertUserToUserDTO(User user) {

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setBirthday(user.getBirthday());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setAddress(user.getAddress());
        dto.setCity(user.getCity());
        dto.setZipCode(user.getZipCode());
        dto.setEmail(user.getEmail());
        return dto;
    }
}