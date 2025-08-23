package com.example.sitpassbek.dto.workday;

import com.example.sitpassbek.enums.DayOfWeekEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkDayDTO {

    @NotNull(message = "validFrom cannot be null")
    private LocalDate validFrom;

    @NotNull(message = "day cannot be null")
    private DayOfWeekEnum day;

    @NotNull(message = "from cannot be null")
    private LocalDateTime from;

    @NotNull(message = "until cannot be null")
    private LocalDateTime until;

}
