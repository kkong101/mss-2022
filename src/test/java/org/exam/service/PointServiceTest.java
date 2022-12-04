package org.exam.service;

import org.exam.common.exception.point.NoEnoughTodayPointException;
import org.exam.common.util.DateTimeUtil;
import org.exam.domain.User;
import org.exam.domain.type.PointType;
import org.exam.repository.PointLogRepository;
import org.exam.repository.UserRepository;
import org.exam.service.Impl.PointServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class PointServiceTest {

    @InjectMocks
    private PointServiceImpl pointService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DateTimeUtil dateTimeUtil;

    @Mock
    private PointLogRepository pointLogRepository;


    private User createUser(Long idx) {
        return User.builder()
                .idx(idx)
                .name("test")
                .pointCurrent(0L)
                .pointLog(new ArrayList<>())
                .build();
    }

    @Test
    @DisplayName("출석하여 100포인트 획득")
    public void attendTodayPointByUser_Every() {

        User user = createUser(1L);

        LocalDateTime todayStart = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0));
        LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));

        when(dateTimeUtil.getTodayStartDateTime()).thenReturn(todayStart);
        when(dateTimeUtil.getTodayEndDateTime()).thenReturn(todayEnd);
        when(pointLogRepository.countByPointTypeIsAndCreatedAtBetween(PointType.EVERY_DAY, todayStart, todayEnd))
                .thenReturn(0);

        when(pointLogRepository.getNextContinuousAttendanceCntByUser(user)).thenReturn(1L);

        pointService.attendTodayPointByUser(user);

        assertThat(user.getPointCurrent()).isEqualTo(100L);
    }

    @Test
    @DisplayName("5일째 출석하여 500포인트 + 100포인트 획득")
    public void attendTodayPointByUser_Five() {

        User user = createUser(1L);

        LocalDateTime todayStart = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0));
        LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));

        when(dateTimeUtil.getTodayStartDateTime()).thenReturn(todayStart);
        when(dateTimeUtil.getTodayEndDateTime()).thenReturn(todayEnd);
        when(pointLogRepository.countByPointTypeIsAndCreatedAtBetween(PointType.EVERY_DAY, todayStart, todayEnd))
                .thenReturn(0);

        when(pointLogRepository.getNextContinuousAttendanceCntByUser(user)).thenReturn(5L);

        List<PointType> result = pointService.attendTodayPointByUser(user);

        assertThat(user.getPointCurrent()).isEqualTo(600L);
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.stream().anyMatch(e -> PointType.EVERY_DAY.equals(e))).isEqualTo(true);
        assertThat(result.stream().anyMatch(e -> PointType.FIVE_DAYS.equals(e))).isEqualTo(true);
    }

    @Test
    @DisplayName("이미 출석 전부 완료되서 에러를 던짐")
    public void attendTodayPointByUser_Already_Complete() {

        User user = createUser(1L);

        LocalDateTime todayStart = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0));
        LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));

        when(dateTimeUtil.getTodayStartDateTime()).thenReturn(todayStart);
        when(dateTimeUtil.getTodayEndDateTime()).thenReturn(todayEnd);
        when(pointLogRepository.countByPointTypeIsAndCreatedAtBetween(PointType.EVERY_DAY, todayStart, todayEnd))
                .thenReturn(10);

        assertThrows(NoEnoughTodayPointException.class, () -> pointService.attendTodayPointByUser(user));
    }

}
