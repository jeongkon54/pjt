package com.kidari.lecture.repository;

import com.kidari.lecture.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HallRepository extends JpaRepository<Hall, Integer> {
    @Query(value = "select capacity_people from hall where lecture_id = :id ", nativeQuery = true)
    int getHallCapa(int id);
}
