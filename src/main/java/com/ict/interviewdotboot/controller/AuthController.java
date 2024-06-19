package com.ict.interviewdotboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.interviewdotboot.service.AuthService;
import com.ict.interviewdotboot.service.UserService;
import com.ict.interviewdotboot.vo.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
public class AuthController {
  
  @Autowired
  private UserService userService;

  @Autowired
  private AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@RequestBody UserVO user) {
    System.out.println("여기는 컨트롤러"+user.getId()+user.getPw());
      return authService.authenticate(user);
  }

  @PostMapping("/create")
  public ResponseEntity<?> createUser(@RequestBody UserVO user) {
    int res = userService.createUser(user);
    return new ResponseEntity<>(res, HttpStatus.CREATED);
  }
  
  @GetMapping("/users")
  public ResponseEntity<List<UserVO>> getGuestList() {
    List<UserVO> users = userService.getUserList();
    return ResponseEntity.ok(users);
  }

  

  

//   @GetMapping("/admins")
//   public ResponseEntity<List<AdminVO>> getAdminList() {
//     List<AdminVO> admins = userService.getAdminList();
//     return ResponseEntity.ok(admins);
// }
}
