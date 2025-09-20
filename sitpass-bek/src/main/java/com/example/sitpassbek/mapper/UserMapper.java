package com.example.sitpassbek.mapper;

import com.example.sitpassbek.dto.user.UserDTO;
import com.example.sitpassbek.model.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<UserDTO> toDtoList(List<User> users) {
        if (users == null) {
            return Collections.emptyList();
        }
        return users.stream()
                .map(this::convertUserToUserDTO)
                .collect(Collectors.toList());
    }

}