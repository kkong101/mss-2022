package org.exam.service;

import org.exam.controller.dto.PointDto;
import org.exam.controller.dto.UserDto;
import org.exam.domain.User;
import org.exam.domain.type.PointType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface PointService {

    List<PointType> attendTodayPointByUser(User user);

    Page<PointDto.PointHistoryElement> getPointHistoryInDateWithPageable(LocalDateTime start, LocalDateTime end, Pageable pageable);

    Page<UserDto.ViewDetail> getPointDetailsByUserByPageable(User user, Pageable pageable);

}
