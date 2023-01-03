package com.kidari.lecture.controller;

import com.kidari.lecture.dto.ResponseDto;
import com.kidari.lecture.dto.ResponseListDto;
import com.kidari.lecture.model.Lecture;
import com.kidari.lecture.model.LectureApply;
import com.kidari.lecture.model.User;
import com.kidari.lecture.service.LectureApplyService;
import com.kidari.lecture.service.LectureService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/lecture")
@RequiredArgsConstructor
public class LectureRestController {
    @Autowired
    private LectureService lectureService;

    @Autowired
    private LectureApplyService lectureApplyService;

    @ApiOperation("강연 목록 전체 조회")
    @GetMapping
    public ResponseEntity<ResponseListDto<Lecture>> getLectureList(HttpServletRequest request) {
        log.info("lecture list");
//        Principal principal = request.getUserPrincipal();
//        User user = userRepository.findByUsername(principal.getName());

        ResponseListDto<Lecture> response = new ResponseListDto<>();

        List<Lecture> lectureList = lectureService.getLectureList();
        response.setData(lectureList);
        response.setCount(lectureList.size());
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }

    @ApiOperation("강연 등록")
    @PostMapping
    public ResponseEntity<ResponseDto<Lecture>> create(@RequestBody Lecture lecture) {
        log.info("request body {} = " + lecture);

        ResponseDto<Lecture> response = new ResponseDto<>();
        response.setSuccess(true);
        response.setData(lectureService.create(lecture));

        return ResponseEntity.ok(response);
    }

    /**
     * 강연 신청
     * @param user
     * @param lectureId
     * @return
     */
    @ApiOperation(value = "강연 신청",
            notes = "강연 신청을 한다")
    @PostMapping("/apply/{lectureId}")
    public ResponseEntity<ResponseDto<String>> apply(@RequestBody User user, @PathVariable int lectureId) {
        log.info("user {} = " + user);

        ResponseDto<String> response = new ResponseDto<>();
        response.setSuccess(true);
        response.setData(lectureApplyService.apply(user, lectureId));

        return ResponseEntity.ok(response);
    }
    @ApiOperation(value = "강연신청 목록 조회",
            notes = "강연신청 목록을 전체 조회한다.")
    @GetMapping("/apply")
    public ResponseEntity<ResponseListDto<Map<String, Object>>> applyList() {

        ResponseListDto<Map<String, Object>> response = new ResponseListDto<>();
        List<Map<String, Object>> applyList = lectureApplyService.applyList();
        response.setData(applyList);
        response.setCount(applyList.size());
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "신청한 강연정보 조회",
            notes = "사번으로 강연정보를 조회한다.")
    @GetMapping("/apply/{userNumber}")
    public ResponseEntity<ResponseListDto<Map<String, Object>>> applyInfo(@PathVariable String userNumber) {
        log.info("request userNumber {} = " + userNumber);

        ResponseListDto<Map<String, Object>> response = new ResponseListDto<>();

        List<Map<String, Object>> list = lectureApplyService.applyInfo(userNumber);

        response.setData(list);
        response.setCount(list.size());
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "강연 취소",
            notes = "강연신청 ID를 통해 강연 취소한다.")
    @PutMapping("/apply/{lectureApplyId}")
    public ResponseEntity<ResponseDto<String>> cancel(@RequestBody User user, @PathVariable Integer lectureApplyId) {
        log.info("request body {} = " + user);

        ResponseDto<String> response = new ResponseDto<>();
        response.setSuccess(true);
        response.setData(lectureApplyService.cancel(user, lectureApplyId));

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "강연 정보 조회",
             notes = "강연 ID를 통해 강연 정보를 조회한다.")
    @ApiImplicitParam(
            name = "id"
            , value = "강연 아이디"
            , required = true
            , defaultValue = "None")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<Lecture>> lectureView(@PathVariable Integer id) {
        log.info("request id {} = " + id);

        ResponseDto<Lecture> response = new ResponseDto<>();
        response.setSuccess(true);
        response.setData( lectureService.lectureView(id) );

        return ResponseEntity.ok(response);
    }


}
