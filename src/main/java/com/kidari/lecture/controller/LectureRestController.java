package com.kidari.lecture.controller;

import com.kidari.lecture.dto.ResponseDto;
import com.kidari.lecture.dto.ResponseListDto;
import com.kidari.lecture.model.Lecture;
import com.kidari.lecture.service.LectureService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/lecture")
@RequiredArgsConstructor
public class LectureRestController {
    @Autowired
    private LectureService lectureService;

    @ApiOperation("강연 목록 전체 조회")
    @GetMapping
    public ResponseEntity<ResponseListDto<Lecture>> getLectureList(HttpServletRequest request) {
        log.info("lecture list");
        Principal principal = request.getUserPrincipal();
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
    public ResponseEntity<ResponseDto<String>> create(@RequestBody Lecture lecture) {
        log.info("request body {} = " + lecture);
//        Principal principal = request.getUserPrincipal();

        ResponseDto<String> response = new ResponseDto<>();
        response.setSuccess(true);
        response.setData(String.valueOf(lectureService.create(lecture)));

        return ResponseEntity.ok(response);
    }

}
