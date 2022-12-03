package org.exam.controller.dto;

import lombok.*;
import org.exam.domain.type.PointType;

import java.time.LocalDateTime;

public class UserDto {

    @Getter
    @NoArgsConstructor
    public static class ViewDetail {
        private Long point;
        private Long pointHistory;
        private PointType pointType;
        private LocalDateTime acquiredAt;

        @Builder
        public ViewDetail(Long point, Long pointHistory, PointType pointType, LocalDateTime acquiredAt) {
            this.point = point;
            this.pointHistory = pointHistory;
            this.pointType = pointType;
            this.acquiredAt = acquiredAt;
        }
    }

}
