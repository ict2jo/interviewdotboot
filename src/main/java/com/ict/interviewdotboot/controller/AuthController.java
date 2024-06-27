package com.ict.interviewdotboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import com.ict.interviewdotboot.jwt.JWTUtil;
import com.ict.interviewdotboot.service.AuthService;
import com.ict.interviewdotboot.service.MyUserDetailsService;
import com.ict.interviewdotboot.service.UserService;
import com.ict.interviewdotboot.service.MailService;

import com.ict.interviewdotboot.vo.DataVO;
import com.ict.interviewdotboot.vo.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


@RestController
@RequestMapping("/api")
public class AuthController {

  @Autowired
  private UserService userService;

  @Autowired
  private AuthService authService;

  @Autowired
  MailService mailService;

  @Autowired
  private JWTUtil jwtUtil;

  @Autowired
  private MyUserDetailsService userDetailsService;

  String randomNumber;

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

  @GetMapping("/idCheck")
  public ResponseEntity<Boolean> checkIdValidation(@RequestParam("id") String id, HttpServletRequest request) {
    UserVO uvo = userService.getUser(id);
    if (uvo != null) {
      return ResponseEntity.ok(true); // true 반환
  } else {
      return ResponseEntity.ok(false); // false 반환
  }
}

@PostMapping("/findPw")
public int pwFindRequest(@RequestBody UserVO user) {
  int userSame = userService.findUserforPw(user);
  if (userSame > 0) {
      randomNumber = randomNum();
      if (randomNumber.length() < 6) {
          int substract = 6 - randomNumber.length();
          StringBuffer sb = new StringBuffer();

          for (int i = 0; i < substract; i++) {
              sb.append("0");
          }
          sb.append(randomNumber);
          randomNumber = sb.toString();
      }
      System.out.println("임시번호: " + randomNumber);
      mailService.sendEmail(randomNumber, user.getEmail());
      return userSame;
  }
  return userSame;
}

public String randomNum() {
  Random random = new Random();
  String randomNumber = String.valueOf(random.nextInt(1000000) % 1000000);
  return randomNumber;
}
}