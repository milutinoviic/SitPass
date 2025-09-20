package com.example.sitpassbek.mapper;

import com.example.sitpassbek.dto.manages.ManagesDTO;
import com.example.sitpassbek.dto.manages.ManagesDetailDTO;
import com.example.sitpassbek.dto.user.UserDTO;
import com.example.sitpassbek.model.Manages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ManagesMapper {

    private final UserMapper userMapper;

    @Autowired
    public ManagesMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public ManagesDTO toDTO(Manages m) {
        ManagesDTO dto = new ManagesDTO();
        dto.setId(m.getId());
        dto.setFacilityId(m.getFacility() != null ? m.getFacility().getId() : null);
        dto.setStartDate(m.getStartDate());
        dto.setEndDate(m.getEndDate());
        dto.setDeleted(m.isDeleted());
        return dto;
    }

    public List<ManagesDTO> toDTOList(List<Manages> managesList) {
        if (managesList == null) {
            return Collections.emptyList();
        }
        return managesList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ManagesDetailDTO toDetailDTO(Manages manages) {
        if (manages == null) return null;

        ManagesDetailDTO dto = new ManagesDetailDTO();
        dto.setId(manages.getId());
        dto.setFacilityId(manages.getFacility() != null ? manages.getFacility().getId() : null);
        dto.setStartDate(manages.getStartDate());
        dto.setEndDate(manages.getEndDate());

        UserDTO userDTO = manages.getUser() != null ? userMapper.convertUserToUserDTO(manages.getUser()) : null;
        dto.setUser(userDTO);

        return dto;
    }

    public List<ManagesDetailDTO> toDetailDTOList(List<Manages> managesList) {
        if (managesList == null) {
            return Collections.emptyList();
        }
        return managesList.stream()
                .map(this::toDetailDTO)
                .collect(Collectors.toList());
    }
}
