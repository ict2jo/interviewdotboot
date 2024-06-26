package com.ict.interviewdotboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ict.interviewdotboot.jwt.JWTUtil;
import com.ict.interviewdotboot.jwt.JwtResponse;
import com.ict.interviewdotboot.vo.DataVO;
import com.ict.interviewdotboot.vo.UserVO;

@Service
public class AuthService {
 
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private MyUserDetailsService userDetailsService;

  @Autowired
  private JWTUtil jwtUtil;

  public DataVO authenticate(UserVO user) {
   DataVO dataVO = new DataVO();
   try {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(user.getId(), user.getPw())
    );

    UserVO uvo = userDetailsService.getUserDetail(user.getId());
    String jwt = jwtUtil.generateToken(user.getId());

    dataVO.setSuccess(true);
    dataVO.setToken(jwt);
    dataVO.setUserdetails(uvo);

    return dataVO;
   } catch (Exception e) {
    dataVO.setSuccess(false);
    dataVO.setMessage(e.getMessage());
    return dataVO;
   }
  }
}
