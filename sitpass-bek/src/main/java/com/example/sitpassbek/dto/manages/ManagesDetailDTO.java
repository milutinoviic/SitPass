package com.example.sitpassbek.dto.manages;

import com.example.sitpassbek.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagesDetailDTO {

    private Long id;

    private Long facilityId;

    private LocalDate startDate;

    private LocalDate endDate;

    private UserDTO user;
}
