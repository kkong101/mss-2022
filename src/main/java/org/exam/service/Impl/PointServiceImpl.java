package org.exam.service.Impl;

import lombok.RequiredArgsConstructor;
import org.exam.common.exception.point.NoEnoughTodayPointException;
import org.exam.common.util.DateTimeUtil;
import org.exam.controller.dto.PointDto;
import org.exam.controller.dto.UserDto;
import org.exam.domain.PointLog;
import org.exam.domain.User;
import org.exam.domain.type.PointType;
import org.exam.repository.PointLogRepository;
import org.exam.repository.UserRepository;
import org.exam.service.PointService;
import org.exam.common.singleton.SingletonPointState;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

    private final PointLogRepository pointLogRepository;

    private final UserRepository userRepository;

    private final DateTimeUtil dateTimeUtil;

    @Value(value = "${mss.point.max-attendance}")
    private Long maxAttendance = 10L;

    @Transactional
    public List<PointType> attendTodayPointByUser(User user) {
        SingletonPointState singletonPointState = SingletonPointState.getInstance();
        LocalDateTime todayStart = dateTimeUtil.getTodayStartDateTime();
        LocalDateTime todayEnd = dateTimeUtil.getTodayEndDateTime();

        Integer todayAttendanceCnt = pointLogRepository.countByPointTypeIsAndCreatedAtBetween(PointType.EVERY_DAY, todayStart, todayEnd);
        boolean isTodayPointAvailable = todayAttendanceCnt < maxAttendance;
        boolean isLastTodayPoint = todayAttendanceCnt == maxAttendance - 1;

        if(!isTodayPointAvailable) {
            singletonPointState.completeTodayPoint();
            throw new NoEnoughTodayPointException();
        }

        Long nextContinuousAttendanceCnt = pointLogRepository.getNextContinuousAttendanceCntByUser(user);

        Optional<PointType> mayBeAdditionalPointType = PointType.findAdditionalPoint(nextContinuousAttendanceCnt);
        List<PointLog> toSavePoints = new ArrayList<>();

        if(mayBeAdditionalPointType.isPresent()) {
            PointType additionalPointType = mayBeAdditionalPointType.get();
            PointLog toSaveAdditionalPointLog = makeNewPointLogInstance(additionalPointType, nextContinuousAttendanceCnt, user);
            toSavePoints.add(toSaveAdditionalPointLog);
        }

        PointLog toSaveAdditionalPointLog = makeNewPointLogInstance(PointType.EVERY_DAY, nextContinuousAttendanceCnt, user);
        toSavePoints.add(toSaveAdditionalPointLog);


        userRepository.save(user);
        pointLogRepository.saveAll(toSavePoints);


        if(isLastTodayPoint)
            singletonPointState.completeTodayPoint();

        return toSavePoints.stream().map(PointLog::getPointType).collect(Collectors.toList());
    }

    @Override
    public Page<PointDto.PointHistoryElement> getPointHistoryInDateWithPageable(LocalDateTime start, LocalDateTime end, Pageable pageable) {
        return pointLogRepository.getPointLogsInDateWithPageable(start, end, pageable);
    }

    @Override
    public Page<UserDto.ViewDetail> getPointDetailsByUserByPageable(User user, Pageable pageable) {
        return pointLogRepository.findAllByUserWithPageable(user, pageable);
    }


    private PointLog makeNewPointLogInstance(PointType pointType, Long newContinuousAttendanceCnt, User user) {
        user.plusPoint(pointType.getPoint());

        return PointLog.builder()
                .continuousAttendanceCnt(newContinuousAttendanceCnt)
                .point(pointType.getPoint())
                .pointType(pointType)
                .userPoint(user.getPointCurrent())
                .user(user)
                .build();
    }

}
