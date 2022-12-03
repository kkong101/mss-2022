package org.exam.common.util;


import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class DateTimeUtil {

    public LocalDateTime getTodayStartDateTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0));
    }

    public LocalDateTime getTodayEndDateTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));
    }

    public LocalDateTime getStartDateTime(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.of(0,0,0));
    }

    public LocalDateTime getEndDateTime(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.of(23,59,59));
    }

}
