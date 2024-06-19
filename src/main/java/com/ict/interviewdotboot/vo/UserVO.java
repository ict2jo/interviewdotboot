package com.ict.interviewdotboot.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserVO {

  //  @JsonProperty("id")
  //   private String u_id;
  //   @JsonProperty("name")   
  //   private String u_name;
  //   @JsonProperty("email")  
  //   private String u_email;
  //   @JsonProperty("pw")  
  //   private String u_pwd;
  //   @JsonProperty("birth")  
  //   private String u_birth;
  //   @JsonProperty("phonenumber")  
  //   private String u_phone;
  private String u_idx,id,name,email,birth, pw, phonenumber, u_regdate,u_status,active,u_report,u_img,Field;
}