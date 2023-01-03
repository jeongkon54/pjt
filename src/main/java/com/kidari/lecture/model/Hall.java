package com.kidari.lecture.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Schema(description = "강연장")
@Table(name = "hall")
@Data
@Entity
@NoArgsConstructor
public class Hall {

    @Schema(description = "시퀀스")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 시퀀스

    @Schema(description = "강연장 이름")
    @Column(name = "hall_name")
    private String hallName;    // 강연장 이름

    @Schema(description = "수용가능인원")
    @Column(name = "capacity_people")
    private int capacityPeople;     // 수용가능인원

    @Schema(description = "강연장 이용 시작 시간", example = "yyyy-MM-dd HH:mm")
    @Column(name = "start_time", length = 25)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTime;    // 강연장 이용 시작 시간

    @Schema(description = "강연장 이용 종료 시간", example = "yyyy-MM-dd HH:mm")
    @Column(name = "end_time", length = 25)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;    // 강연장 종료 시간

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "lecture_id")
//    private Lecture lecture;

    @CreationTimestamp // 자동으로 현재 시간이 세팅
    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createDate;

    @UpdateTimestamp // 자동으로 현재 시간이 세팅
    @Column(name = "update_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateDate;
}
