package com.kidari.lecture.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Schema(description = "강연")
@Table(name = "lecture")
@Data
@Entity
@NoArgsConstructor
public class Lecture {

    @Schema(description = "시퀀스")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 시퀀스

    @Schema(description = "강연자")
    @Column(name = "lecturer")
    private String lecturer;    // 강연자

    @Schema(description = "강연장")
    @Column(name = "lecture_hall")
    private String lectureHall; // 강연장

    @Schema(description = "신청인원")
    @Column(name = "sign_up_people")
    private Integer singUpPeople;    // 신청인원

    @Schema(description = "강연시간", example = "yyyy-MM-dd HH:mm")
    @Column(name = "lecture_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date lectureTime;    // 강연시간

    @Schema(description = "강연내용")
    @Column(name = "lecture_content", columnDefinition = "TEXT")
    private String lectureContent;    // 강연내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @CreationTimestamp // 자동으로 현재 시간이 세팅
    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createDate;

    @UpdateTimestamp // 자동으로 현재 시간이 세팅
    @Column(name = "update_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateDate;

}
