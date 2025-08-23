package com.example.sitpassbek.dto.workdayDTO;

import com.example.sitpassbek.enums.DayOfWeekEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkDayDTO {

    private Long id;
    private LocalDate validFrom;
    private DayOfWeekEnum day;
    private LocalDateTime from;
    private LocalDateTime until;
}