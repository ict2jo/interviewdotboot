package com.ict.interviewdotboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ict.interviewdotboot.vo.UserVO;

@Mapper
public interface UserMapper {

  int createUser(UserVO UserVO);
  UserVO selectUser(@Param("id") String id);
  List<UserVO> getUserList();


  // AdminVO selectAdmin(@Param("a_idx") String a_idx);


  // List<AdminVO> getAdminList();
}
