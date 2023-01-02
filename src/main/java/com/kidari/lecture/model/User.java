package com.kidari.lecture.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "사용자")
@Table(name = "user")
@Data
@Entity
@NoArgsConstructor
public class User {

    @Schema(description = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Schema(description = "사번")
    @Column(name = "user_number")
    private String userNumber;    // 사번

    @Schema(description = "이름")
    @Column(name = "user_name")
    private String userName; // 이름

    @Schema(description = "성별")
    @Column(name = "gender")
    private String gender;    // 성별

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Lecture> lecture = new ArrayList<>();

    @CreationTimestamp // 자동으로 현재 시간이 세팅
    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createDate;

    @UpdateTimestamp // 자동으로 현재 시간이 세팅
    @Column(name = "update_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateDate;

    public User(String userNumber){
        this.userNumber = userNumber;
    }
}
