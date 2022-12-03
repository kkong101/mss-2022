package org.exam.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.exam.domain.type.PointType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PointDto {

    @Getter
    @NoArgsConstructor
    public static class PointAttendance {

        private List<PointType> pointType = new ArrayList<>();
        private Long currentPoint;

        @Builder
        public PointAttendance(List<PointType> pointType, Long currentPoint) {
            this.pointType = pointType;
            this.currentPoint = currentPoint;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class PointHistoryElement {

        private String userName;
        private Long point;
        private Long pointHistory;
        private PointType pointType;
        private LocalDateTime acquiredAt;

        @Builder
        public PointHistoryElement(String userName, Long point, Long pointHistory, PointType pointType, LocalDateTime acquiredAt) {
            this.userName = userName;
            this.pointHistory = pointHistory;
            this.pointType = pointType;
            this.acquiredAt = acquiredAt;
            this.point = point;
        }
    }


}
