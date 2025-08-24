package com.example.sitpassbek.mapper;

import com.example.sitpassbek.dto.accountRequest.AccountRequestDTO;
import com.example.sitpassbek.model.AccountRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountRequestMapper {

    public AccountRequestDTO toDto(AccountRequest entity) {
        if (entity == null) return null;

        AccountRequestDTO dto = new AccountRequestDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setAddress(entity.getAddress());
        dto.setStatus(entity.getStatus().name());
        dto.setRejectionReason(entity.getRejectionReason());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public List<AccountRequestDTO> toDtoList(List<AccountRequest> entities) {
        if (entities == null) return null;

        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
