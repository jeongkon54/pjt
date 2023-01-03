package com.kidari.lecture.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Schema(description = "강연신청 이력")
@Table(name = "lecture_apply")
@Data
@Entity
@NoArgsConstructor
public class LectureApply {

    @Schema(description = "시퀀스")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 시퀀스

    @Schema(description = "강연 신청 상태", example = "A: 신청, C: 취소")
    @Column(name = "stats")
    private String stats;           // 강연 신청 상태

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @CreationTimestamp // 자동으로 현재 시간이 세팅
    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createDate;

    @UpdateTimestamp // 자동으로 현재 시간이 세팅
    @Column(name = "update_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateDate;
}
