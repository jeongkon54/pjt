package com.kidari.lecture.repository;

import com.kidari.lecture.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {
    @Query(value = "select * from lecture where id = ?1 ", nativeQuery = true)
    Lecture findLectureId(int id);

}
