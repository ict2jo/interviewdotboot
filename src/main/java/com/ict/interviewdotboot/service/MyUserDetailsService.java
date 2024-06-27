package com.ict.interviewdotboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import com.ict.interviewdotboot.mapper.UserMapper;
import com.ict.interviewdotboot.vo.UserVO;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserVO member = userMapper.selectUser(id);
        if(member.getPw() == null) {
            
            return new User(member.getId(), "", new ArrayList<>());
        }
        return new User(member.getId(), member.getPw(), new ArrayList<>());
    }


     // DB에서 개인정보 추출 (/api/login, /api/uerInfo 후 AuthController에서 호출 )
     public UserVO getUserDetail(String id) throws Exception{
        UserVO uvo = userMapper.selectUser(id);
        return uvo ;
    }

    public UserDetails loadUserByOAuth2User(OAuth2User oAuth2User,String provoider){
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String phone = oAuth2User.getAttribute("phone");
        String id = "";

        if(provoider.equals("kakao")){
            Long kakaoId = oAuth2User.getAttribute("id");
            id = String.valueOf(kakaoId);
        }else{
            id = oAuth2User.getAttribute("id");
        }

        // 해당 이메일로 DB에 정보가 있는지 확인
        UserVO uvo = userMapper.findUserByEmail(email);

        if(uvo == null){
            // 신규 (insert)
            uvo = new UserVO();
            uvo.setId(id);
            uvo.setEmail(email);
            uvo.setProvider(provoider);
            uvo.setName(name != null ? name : "hong");
            uvo.setPhonenumber(phone != null ? phone : "000-0000-0000");

            if(provoider.equals("kakao")){
                uvo.setKakao(email);
            }else if(provoider.equals("naver")){
                uvo.setNaver(email);
            }else if(provoider.equals("google")){
                uvo.setGoogle(email);
            }

            userMapper.insertUser(uvo);
        }else{
            // 기존(update)
            userMapper.updateUser(uvo);
        }

        return new User(uvo.getId(), "", new ArrayList<>());
    }
}
