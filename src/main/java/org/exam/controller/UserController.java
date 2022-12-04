package org.exam.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.exam.common.constants.SortConstants;
import org.exam.common.exception.point.NoEnoughTodayPointException;
import org.exam.controller.dto.CustomResponse;
import org.exam.controller.dto.PointDto;
import org.exam.controller.dto.UserDto;
import org.exam.domain.User;
import org.exam.domain.type.PointType;
import org.exam.service.PointService;
import org.exam.common.singleton.SingletonPointState;
import org.exam.service.UserService;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final PointService pointService;

    private final UserService userService;

    @GetMapping(value = "/{userId}/point")
    public CustomResponse getUserPointInfo(@PathVariable Long userId, @PageableDefault Pageable pageable) {

        User user = userService.getUserByIdx(userId);

        if(pageable.getSort().isEmpty() || Objects.isNull(pageable.getSort().getOrderFor(SortConstants.DATE))) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(SortConstants.DATE).descending());
        }

        Page<UserDto.ViewDetail> result = pointService.getPointDetailsByUserByPageable(user, pageable);

        return CustomResponse.ok(result);
    }

    @PostMapping(value = "/{userId}/attendance")
    public CustomResponse attendance(@PathVariable Long userId) {

        User user = userService.getUserByIdx(userId);

        SingletonPointState singletonPointState = SingletonPointState.getInstance();

        if(!singletonPointState.getIsTodayPointAvailable())
            throw new NoEnoughTodayPointException();

        List<PointType> savedPointTypes = new ArrayList<>();

        synchronized (this) {
            savedPointTypes.addAll(pointService.attendTodayPointByUser(user));
        }

        savedPointTypes.addAll(pointService.attendTodayPointByUser(user));

        PointDto.PointAttendance response = PointDto.PointAttendance.builder()
                .pointType(savedPointTypes)
                .currentPoint(user.getPointCurrent())
                .build();

        return CustomResponse.ok(response);
    }

}
