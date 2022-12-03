package org.exam.repository.custom.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.exam.common.constants.SortConstants;
import org.exam.common.exception.point.AlreadyGotTodayPointException;
import org.exam.common.util.DateTimeUtil;
import org.exam.controller.dto.PointDto;
import org.exam.controller.dto.UserDto;
import org.exam.domain.PointLog;
import org.exam.domain.QPointLog;
import org.exam.domain.QUser;
import org.exam.domain.User;
import org.exam.domain.type.PointType;
import org.exam.repository.custom.PointLogCustomRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class PointLogCustomRepositoryImpl implements PointLogCustomRepository {

    private final JPAQueryFactory queryFactory;

    private final DateTimeUtil dateTimeUtil;

    @Value(value = "${mss.point.max-attendance}")
    private Long maxAttendance;

    QPointLog qPointLog = QPointLog.pointLog;
    QUser qUser = QUser.user;

    @Override
    public Long getNextContinuousAttendanceCntByUser(User user) {

        List<PointLog> result = queryFactory.selectFrom(qPointLog)
                .where(qPointLog.idx.goe(
                        JPAExpressions.select(qPointLog.idx.max()).from(qPointLog)
                                .where(qPointLog.user.eq(user)
                                        .and(qPointLog.continuousAttendanceCnt.eq(1L)))
                ).and(qPointLog.pointType.eq(PointType.EVERY_DAY))).orderBy(qPointLog.idx.desc()).fetch();

        boolean isAttendToday = result.stream()
                .anyMatch(e -> e.getCreatedAt().isAfter(dateTimeUtil.getTodayStartDateTime())
                        && e.getCreatedAt().isBefore(dateTimeUtil.getTodayEndDateTime()));
        if(isAttendToday) throw new AlreadyGotTodayPointException();

        Optional<PointLog> mayBeYesterdayPointLog = result.stream()
                .filter(e -> e.getCreatedAt().isAfter(dateTimeUtil.getTodayStartDateTime().minusDays(1L))
                                    && e.getCreatedAt().isBefore(dateTimeUtil.getTodayEndDateTime().minusDays(1L)))
                .findFirst();

        boolean isNeedToReset = result.stream()
                .anyMatch(e -> e.getContinuousAttendanceCnt().equals(maxAttendance));

        if(mayBeYesterdayPointLog.isEmpty() || isNeedToReset) return 1L;

        return mayBeYesterdayPointLog.get().getContinuousAttendanceCnt() + 1L;
    }

    @Override
    public Page<PointDto.PointHistoryElement> getPointLogsInDateWithPageable(LocalDateTime start, LocalDateTime end, Pageable pageable) {

        List<PointDto.PointHistoryElement> result = queryFactory.select(
                        Projections.constructor(PointDto.PointHistoryElement.class,
                                qUser.name,
                                qPointLog.point,
                                qPointLog.userPoint,
                                qPointLog.pointType,
                                qPointLog.createdAt)
                )
                .from(qPointLog)
                .innerJoin(qPointLog.user, qUser)
                .where(qPointLog.createdAt.between(start, end))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(pageable.getSort().getOrderFor(SortConstants.DATE).isAscending() ?
                        qPointLog.createdAt.asc() : qPointLog.createdAt.desc())
                .fetch();

        int total = queryFactory.selectOne().from(qPointLog)
                .where(qPointLog.createdAt.between(start, end))
                .fetch().size();

        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public Page<UserDto.ViewDetail> findAllByUserWithPageable(User user, Pageable pageable) {

        List<UserDto.ViewDetail> result = queryFactory.select(
                        Projections.constructor(UserDto.ViewDetail.class,
                                qPointLog.point,
                                qPointLog.userPoint,
                                qPointLog.pointType,
                                qPointLog.createdAt)
                )
                .from(qPointLog)
                .where(qPointLog.user.eq(user))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(pageable.getSort().getOrderFor(SortConstants.DATE).isAscending() ?
                        qPointLog.createdAt.asc() : qPointLog.createdAt.desc())
                .fetch();

        int total = queryFactory.selectOne().from(qPointLog)
                .where(qPointLog.user.eq(user))
                .fetch().size();

        return new PageImpl<>(result, pageable, total);
    }
}
