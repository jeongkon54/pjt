package com.kidari.lecture.service;

import com.kidari.lecture.model.Lecture;
import com.kidari.lecture.repository.LectureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
//        Lecture lt = lectureRepository.findLectureId(lecture.getId());
//
//        if (ObjectUtils.isEmpty(lt)) {
//            throw new ApiParameterException("등록된 강의가 없습니다.");
//        }
//        if(lt.getId() == lecture.getId()) {
//            int cnt = lt.getSingUpPeople();
//            cnt = cnt + 1 ;
//            lecture.setSingUpPeople(cnt);
//        }

//        lecture = lectureRepository.save(lecture);
//        return modelMapper.map(lecture, Lecture.class);
        return lectureRepository.save(lecture);
    }
}
