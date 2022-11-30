package org.exam.domain;


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

    @Column(name = "name")
    private String name;

    @Column(name = "created_at", columnDefinition = "DATETIME")
    private final LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "point", fetch = FetchType.LAZY)
    private List<Point> point = new ArrayList<>();


}
