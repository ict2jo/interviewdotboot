package com.ict.interviewdotboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import com.ict.interviewdotboot.vo.UserVO;

@Mapper
public interface UserMapper {

  int createUser(UserVO uvo);
  UserVO selectUser(@Param("id") String id);
  UserVO getUser(String id);
  List<UserVO> getUserList();
  UserVO findUserByEmail(@Param("email") String email);
  int findUserforPw(UserVO uvo);
  void insertUser(UserVO uvo);
  void updateUser(UserVO uvo);
  int updateUserPassword(String id, String pw);
  int resetPw(UserVO user);
}



