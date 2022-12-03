package org.exam.controller;


import lombok.RequiredArgsConstructor;
import org.exam.common.constants.SortConstants;
import org.exam.common.util.DateTimeUtil;
import org.exam.controller.dto.CustomResponse;
import org.exam.controller.dto.PointDto;
import org.exam.service.PointService;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@RequestMapping("points")
@RequiredArgsConstructor
public class PointController {

    private final DateTimeUtil dateTimeUtil;

    private final PointService pointService;

    @GetMapping(value = "/history")
    public CustomResponse getPointInfo(
            @RequestParam(name="date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate targetDate,
            @PageableDefault Pageable pageable) {

        if(pageable.getSort().isEmpty() || Objects.isNull(pageable.getSort().getOrderFor(SortConstants.DATE))) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(SortConstants.DATE).descending());
        }

        LocalDateTime startDateTime = dateTimeUtil.getStartDateTime(targetDate);
        LocalDateTime endDateTime = dateTimeUtil.getEndDateTime(targetDate);

        Page<PointDto.PointHistoryElement> result = pointService.getPointHistoryInDateWithPageable(startDateTime, endDateTime, pageable);

        return CustomResponse.ok(result);
    }





}
