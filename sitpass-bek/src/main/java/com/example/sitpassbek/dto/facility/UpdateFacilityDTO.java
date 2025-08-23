package com.example.sitpassbek.dto.facility;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFacilityDTO {

    @NotNull(message = "name cannot be null")
    @Size(min = 3, message = "name must be at least 3 characters")
    private String name;

    @NotNull(message = "description cannot be null")
    @Size(min = 3, message = "description must be at least 3 characters")
    private String description;

    @NotNull(message = "address cannot be null")
    @Size(min = 3, message = "address must be at least 3 characters")
    private String address;

    @NotNull(message = "city cannot be null")
    @Size(min = 3, message = "city must be at least 3 characters")
    private String city;

}
