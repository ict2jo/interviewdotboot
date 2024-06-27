package com.ict.interviewdotboot.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ict.interviewdotboot.mapper.UserMapper;
import com.ict.interviewdotboot.vo.UserVO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
  @Autowired
  private UserMapper userMapper;

  @Autowired
  private PasswordEncoder passwordEncoder;

  private Map<String, String> authCodeStore = new ConcurrentHashMap<>();

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

  public UserVO selectUser(String id) {
    return userMapper.selectUser(id);
  }

  public UserVO getUser(String id) {
    return userMapper.getUser(id);
  }

  public int findUserforPw(UserVO user) {
    return userMapper.findUserforPw(user);
}

public boolean verifyAuthCode(String email, String authCode) {
  String storedCode = authCodeStore.get(email);
  return storedCode != null && storedCode.equals(authCode);
}

public void storeAuthCode(String email, String authCode) {
  authCodeStore.put(email, authCode);
}

public int resetPw(UserVO user) {
  String encodedPw = passwordEncoder.encode(user.getPw());
  UserVO newUser = new UserVO();
  newUser.setPw(encodedPw);
  newUser.setId(user.getId());
  return userMapper.resetPw(newUser);
}
}
