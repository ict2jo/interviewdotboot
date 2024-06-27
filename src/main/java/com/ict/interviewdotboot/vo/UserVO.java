package com.ict.interviewdotboot.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UserVO implements UserDetails{

  private String u_idx,id,name,email,birth, pw, phonenumber, u_regdate,u_status,active,u_report,u_img,Field;
private String kakao = "";
  private String naver = "";
  private String google = "";
  private String provider = "";

  private List<GrantedAuthority> authorities = new ArrayList<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {       return authorities;    }
    @Override
    public String getPassword() {          return pw;    }
    @Override
    public String getUsername() {        return id;    }
    @Override
    public boolean isAccountNonExpired() {        return true;    }
    @Override
    public boolean isAccountNonLocked() {        return true;    }
    @Override
    public boolean isCredentialsNonExpired() {        return true;    }
    @Override
    public boolean isEnabled() {        return true;    }
 
}