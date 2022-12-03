package org.exam.repository;

import org.exam.domain.PointLog;
import org.exam.domain.User;
import org.exam.domain.type.PointType;
import org.exam.repository.custom.PointLogCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PointLogLogRepository extends JpaRepository<PointLog, Long> , PointLogCustomRepository {
    Integer countByPointTypeIsAndCreatedAtBetween(PointType pointType, LocalDateTime startDateTime, LocalDateTime endDateTime);

}
