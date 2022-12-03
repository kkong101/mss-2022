package org.exam.repository.custom;

import org.exam.controller.dto.PointDto;
import org.exam.controller.dto.UserDto;
import org.exam.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface PointLogCustomRepository {

    Long getNextContinuousAttendanceCntByUser(User user);

    Page<PointDto.PointHistoryElement> getPointLogsInDateWithPageable(LocalDateTime start, LocalDateTime end, Pageable pageable);

    Page<UserDto.ViewDetail> findAllByUserWithPageable(User user, Pageable pageable);

}
