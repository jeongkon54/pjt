package com.kidari.lecture.repository;

import com.kidari.lecture.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {
    @Query(value = "select * from lecture where id = ?1 ", nativeQuery = true)
    Lecture findLectureId(int id);

    @Query(value = "select l.id , l.lecture_content  from lecture_apply la , lecture l " +
            " where la.lecture_id = l.id " +
            " and la.create_date < now() and la.create_date > DATE_ADD(NOW(), INTERVAL -3 DAY) " +
            " and la.stats = 'A' " +
            " group by l.id " +
            " order by l.sign_up_people DESC ", nativeQuery = true)
    List<Map<String, Object>> getLectureInterest();
}
