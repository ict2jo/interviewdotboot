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
import com.ict.interviewdotboot.vo.UserVO;

@Service
public class AuthService {
 
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JWTUtil jwtUtil;

  public ResponseEntity<?> authenticate(UserVO user) {
   try {
     Authentication authentication = authenticationManager.authenticate(
       new UsernamePasswordAuthenticationToken(user.getId(), user.getPw()));
      
          final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getId());
        final String jwt = jwtUtil.generateToken(userDetails);
        System.out.println(jwt);
        return ResponseEntity.ok(new JwtResponse(jwt));
      
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
    }
  }
}
