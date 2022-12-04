package org.exam.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "mss.USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "name", columnDefinition = "VARCHAR(20)")
    private String name;

    @Column(name = "point_current", columnDefinition = "INT")
    private Long pointCurrent;

    @Column(name = "created_at", columnDefinition = "DATETIME")
    private final LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "point", fetch = FetchType.LAZY)
    private List<PointLog> pointLog = new ArrayList<>();

    @Builder
    public User(Long idx, String name, Long pointCurrent, List<PointLog> pointLog) {
        this.idx = idx;
        this.name = name;
        this.pointCurrent = pointCurrent;
        this.pointLog = pointLog;
    }

    public synchronized void plusPoint(Long newPoint) {
        this.pointCurrent += newPoint;
    }

}
