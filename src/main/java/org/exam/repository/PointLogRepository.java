package org.exam.repository;

import org.exam.domain.PointLog;
import org.exam.domain.type.PointType;
import org.exam.repository.custom.PointLogCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface PointLogRepository extends JpaRepository<PointLog, Long> , PointLogCustomRepository {
    Integer countByPointTypeIsAndCreatedAtBetween(PointType pointType, LocalDateTime startDateTime, LocalDateTime endDateTime);

}
