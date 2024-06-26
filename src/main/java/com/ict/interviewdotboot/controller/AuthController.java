package com.ict.interviewdotboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ict.interviewdotboot.jwt.JWTUtil;
import com.ict.interviewdotboot.service.AuthService;
import com.ict.interviewdotboot.service.MyUserDetailsService;
import com.ict.interviewdotboot.service.UserService;
import com.ict.interviewdotboot.vo.DataVO;
import com.ict.interviewdotboot.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthController {

  @Autowired
  private UserService userService;

  @Autowired
  private AuthService authService;

  @Autowired
  private JWTUtil jwtUtil;

  @Autowired
  private MyUserDetailsService userDetailsService;

  @PostMapping("/login")
  public ResponseEntity<DataVO> loginUser(@RequestBody UserVO user) {
    DataVO dataVO = authService.authenticate(user);
    if (dataVO != null) {
      return ResponseEntity.ok(dataVO);
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
  }

  @PostMapping("/create")
  public ResponseEntity<?> createUser(@RequestBody UserVO user) {
    int res = userService.createUser(user);
    System.out.println(res);
    return new ResponseEntity<>(res, HttpStatus.CREATED);
  }

  @GetMapping("/users")
  public ResponseEntity<List<UserVO>> getGuestList() {
    List<UserVO> users = userService.getUserList();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/user")
  public ResponseEntity<?> getUser(@RequestParam("id") String id, HttpServletRequest request) {
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      String headerValue = request.getHeader(headerName);
      System.out.println(headerName + ": " + headerValue);
    }

    UserVO userLoggedIn = userService.getUser(id);
    return ResponseEntity.ok(userLoggedIn);
  }

  @GetMapping("/userInfo")
  public ResponseEntity<UserVO> getUserInfo(@RequestParam("token") String token) throws Exception {
    String id = jwtUtil.extractUsername(token);
    UserVO user = userDetailsService.getUserDetail(id);
    System.out.println("user: "+user);
    if (user != null) {
      return ResponseEntity.ok(user);
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
  }


}
