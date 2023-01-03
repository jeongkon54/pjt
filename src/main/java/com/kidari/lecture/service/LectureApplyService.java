package com.kidari.lecture.service;

import com.kidari.lecture.config.exception.ApiParameterException;
import com.kidari.lecture.model.Lecture;
import com.kidari.lecture.model.LectureApply;
import com.kidari.lecture.model.User;
import com.kidari.lecture.repository.HallRepository;
import com.kidari.lecture.repository.LectureApplyRepository;
import com.kidari.lecture.repository.LectureRepository;
import com.kidari.lecture.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class LectureApplyService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private LectureApplyRepository lectureApplyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HallRepository hallRepository;

    @Transactional
    @Modifying
    public String apply(User user, int lectureId) {
        log.info(" 강의 신청");
        String result = "신청에 실패하였습니다.";    // 실패

        boolean check = checkLectureAppy(user, lectureId);
        log.info("check : " + check);

        LectureApply lta = new LectureApply();
        Lecture lt = lectureRepository.findLectureId(lectureId);

        if(ObjectUtils.isEmpty(lt)) {
            throw new ApiParameterException("등록된 강의가 없습니다.");
        }
        int cnt = lt.getSingUpPeople();
        lt.setSingUpPeople(cnt);
        log.info("신청한 인원 수 : " + cnt);

        int capa = hallRepository.getHallCapa(lectureId);
        log.info("수용 가능 한 인원 수 : " + capa);

        if(check) {
            if(cnt < capa) {
                lta.setStats("A");  // 신청 코드
                lta.setUser(user);
                lta.setLecture(lt);
                lectureApplyRepository.save(lta);   // 수강신청 등록
                lt.setSingUpPeople(cnt+1);
                lectureRepository.save(lt);         // 신청인원 업데이트 처리
                result = "신청되었습니다.";
            } else {
                throw new ApiParameterException("강연장 수용인원이 초과 되었습니다.");
            }
        } else {
            throw new ApiParameterException("강연신청이 안되었습니다.");
        }

        return result;
    }

    /**
     * 강연 신청 여부 체크
     * @param user
     * @param lectureId
     * @return
     */
    private boolean checkLectureAppy(User user, int lectureId) {
        boolean result = false;

        LectureApply la = lectureApplyRepository.checkLectureApply(user.getId(), lectureId);

        if(!ObjectUtils.isEmpty(la)) {
            throw new ApiParameterException("이미 신청하였습니다.");
        } else {
            result = true;
        }

        return result;
    }

    @Transactional
    @Modifying
    public String cancel(User user, int lectureApplyId) {
        String result = "취소가 안되었습니다.";

        try {
            LectureApply lta = lectureApplyRepository.findByUserId(user.getId());
            Lecture lt = lectureRepository.findLectureId(lta.getLecture().getId());

            if(ObjectUtils.isEmpty(lta))
                throw new ApiParameterException("신청한 강연 이력이 없습니다.");

            if(lta.getId() == lectureApplyId && lta.getStats().equals("A")) {
                lta.setStats("C");  // 취소 처리
                lectureApplyRepository.save(lta);   // 변경 처리 신청 -> 취소

                int cnt = lt.getSingUpPeople();
                log.info("신청 인원 cnt : " + cnt);
                if(cnt > 0) {
                    int minCnt = cnt-1;
                    log.info("신청 인원  -1  : " + minCnt);

                    lt.setSingUpPeople(minCnt);
                    lectureRepository.save(lt); // 신청인원 변경처리
                }

                result = "취소 처리 되었습니다.";

            } else {
                throw new ApiParameterException("이미 취소 하였거나, 신청한 강연 이력이 없습니다.");
            }

        } catch (Exception e) {
            result  = "실패하였습니다.";
            throw new ApiParameterException(result);
        }

        return result;
    }

    public List<Map<String, Object>> applyInfo(String userNumber) {
        User user = userRepository.findByUserNumber(userNumber);

        if(ObjectUtils.isEmpty(user))
            throw new ApiParameterException("존재하지 않는 사번입니다.");

        return lectureApplyRepository.selectApplyInfo(user.getId());
    }

    public List<Map<String, Object>> applyList() {
        return lectureApplyRepository.selectApplyList();
    }

}
