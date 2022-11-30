package org.exam.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.exam.domain.type.PointType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "mss.POINT")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", columnDefinition = "INT")
    private Long idx;

    @Column(name = "point", columnDefinition = "INT")
    private Long point;

    @Column(name = "point_code", columnDefinition = "CHAR(2)")
    @Convert(converter = PointType.TypeConvert.class)
    private PointType pointType;

    @Column(name = "continuous_attendance_cnt", columnDefinition = "INT")
    private Long ContinuousAttendanceCnt;

    @Column(name = "created_at", columnDefinition = "DATETIME")
    private final LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_user", columnDefinition = "INT")
    private User user;

    @Builder
    public Point(Long idx, Long point, PointType pointType, Long continuousAttendanceCnt, User user) {
        this.idx = idx;
        this.point = point;
        this.pointType = pointType;
        ContinuousAttendanceCnt = continuousAttendanceCnt;
        this.user = user;
    }
}
