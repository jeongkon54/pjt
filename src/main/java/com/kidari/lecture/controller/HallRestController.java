package com.kidari.lecture.controller;

import com.kidari.lecture.dto.ResponseDto;
import com.kidari.lecture.dto.ResponseListDto;
import com.kidari.lecture.model.Hall;
import com.kidari.lecture.model.Lecture;
import com.kidari.lecture.service.HallService;
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
@RequestMapping("/api/hall")
@RequiredArgsConstructor
public class HallRestController {

    @Autowired
    private HallService hallService;

    @ApiOperation("강연장 조회")
    @GetMapping
    public ResponseEntity<ResponseListDto<Hall>> getHallList(HttpServletRequest request) {
        log.info("hall list");
        ResponseListDto<Hall> response = new ResponseListDto<>();

        List<Hall> hallList = hallService.getHallList();
        response.setData(hallList);
        response.setCount(hallList.size());
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }


    @ApiOperation("강연장 생성")
    @PostMapping
    public ResponseEntity<ResponseDto<Hall>> create(HttpServletRequest request,
                                                    @RequestBody Hall hall) {
        log.info("request body {} = " + hall);

        ResponseDto<Hall> response = new ResponseDto<>();
        response.setSuccess(true);
        response.setData(hallService.create(hall));

        return ResponseEntity.ok(response);
    }
}
