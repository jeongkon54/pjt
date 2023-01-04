package com.kidari.lecture.service;

import com.kidari.lecture.config.exception.ApiParameterException;
import com.kidari.lecture.model.Lecture;
import com.kidari.lecture.repository.LectureRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class LectureService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LectureRepository lectureRepository;

    public List<Lecture> getLectureList() {
        return lectureRepository.findAll();
    }

    @Transactional
    public Lecture create(Lecture lecture) {
        log.info(" 강의 생성 !!! ");
        return lectureRepository.save(lecture);
    }

    public Lecture lectureView(Integer id) {
        Lecture lt = lectureRepository.findLectureId(id);

        if(ObjectUtils.isEmpty(lt))
            throw new ApiParameterException("등록된 강의가 없습니다.");

        return lt;
    }

    public List<Map<String, Object>> getLectureInterest() {
        return lectureRepository.getLectureInterest();
    }
}
