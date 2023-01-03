package com.kidari.lecture.controller;

import com.kidari.lecture.dto.ResponseDto;
import com.kidari.lecture.dto.ResponseListDto;
import com.kidari.lecture.model.User;
import com.kidari.lecture.service.UserService;
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
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {
    @Autowired
    private UserService userService;

    @ApiOperation("사용자 조회")
    @GetMapping
    public ResponseEntity<ResponseListDto<User>> getUserList(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();

        ResponseListDto<User> response = new ResponseListDto<>();

        List<User> userList = userService.getUserList();
        response.setData(userList);
        response.setCount(userList.size());
        response.setSuccess(true);

        return ResponseEntity.ok(response);
    }

    @ApiOperation("사용자 등록")
    @PostMapping
    public ResponseEntity<ResponseDto<User>> register(HttpServletRequest request,
                                                      @RequestBody User user) {
        log.info("user {} : " + user);

        ResponseDto<User> response = new ResponseDto<>();
        response.setSuccess(true);
        response.setData(userService.register(user));

        return ResponseEntity.ok(response);
    }


}
