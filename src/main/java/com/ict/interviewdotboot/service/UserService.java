package com.ict.interviewdotboot.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ict.interviewdotboot.mapper.UserMapper;
import com.ict.interviewdotboot.vo.UserVO;

import java.util.List;

@Service
public class UserService {
  @Autowired
  private UserMapper userMapper;

  @Autowired

  private PasswordEncoder passwordEncoder;

  public int createUser(UserVO user){

    String encodedPw = passwordEncoder.encode(user.getPw());
    UserVO newUser = new UserVO();
    newUser.setPw(encodedPw);
    newUser.setBirth(user.getBirth());
    newUser.setEmail(user.getEmail());
    newUser.setId(user.getId());
    newUser.setName(user.getName());
    newUser.setPhonenumber(user.getPhonenumber());
    int res = userMapper.createUser(newUser);
    return res;
  }
  
  public List<UserVO> getUserList() {

    return userMapper.getUserList();
  }

  public UserVO getUser(String id) {
    return userMapper.selectUser(id);
  }

  // public List<AdminVO> getAdminList() {
  //   return userMapper.getAdminList();
  // }
}
