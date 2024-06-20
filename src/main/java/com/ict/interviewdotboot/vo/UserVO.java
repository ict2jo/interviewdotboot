package com.ict.interviewdotboot.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserVO {

  private String u_idx,id,name,email,birth, pw, phonenumber, u_regdate,u_status,active,u_report,u_img,Field;
}