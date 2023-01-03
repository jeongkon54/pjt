package com.kidari.lecture.repository;

import com.kidari.lecture.model.LectureApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface LectureApplyRepository extends JpaRepository<LectureApply, Integer> {
    @Query(value = "select * from lecture_apply where user_id = ?1 ", nativeQuery = true)
    LectureApply findByUserId(int id);

    @Query(value = "select * from lecture_apply where user_id = :userId and lecture_id = :lectureId", nativeQuery = true)
    LectureApply checkLectureApply(int userId, int lectureId);

    @Query(value = "select  " +
            " l.lecture_content as lectureContent , " +
            " l.lecturer , " +
            " h.hall_name as hallName , " +
            " l.start_time as startTime , " +
            " l.end_time  as endTime " +
            " FROM  " +
            " lecture_apply la , lecture l  " +
            " left outer join hall h  " +
            " on h.lecture_id = l.id  " +
            " where la.lecture_id = l.id  " +
            " and la.user_id = :id ", nativeQuery = true)
    List<Map<String, Object>> selectApplyInfo(int id);

    @Query(value = "select la.id as lectureApplyId, la.create_date as createDate , la.update_date as updateDate, " +
            "u.user_name as userName , u.user_number as userNumber, " +
            "(select l.lecture_content  from lecture l where l.id = la.lecture_id) as lectureContent " +
            "from lecture_apply la, user u  " +
            "where la.user_id = u.id  " +
            "and la.stats = 'A' ", nativeQuery = true)
    List<Map<String, Object>> selectApplyList();
}
