package com.ict.interviewdotboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        if (member == null) {
            throw new UsernameNotFoundException("User not found with username: " + id);
        }
        return new User(member.getId(), member.getPw(), new ArrayList<>());
    }
}
