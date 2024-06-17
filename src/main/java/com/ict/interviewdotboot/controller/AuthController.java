package com.ict.interviewdotboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.interviewdotboot.service.AdminService;
import com.ict.interviewdotboot.service.AuthService;
import com.ict.interviewdotboot.service.GuestService;
import com.ict.interviewdotboot.vo.GuestVO;
import com.ict.interviewdotboot.vo.MembersVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;


@RestController
@RequestMapping("/api")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @Autowired
    private GuestService guestService;

    @Autowired
    private AdminService adminService;
    

    @PostMapping("/login")
    public ResponseEntity<?> postMethodName(@RequestBody MembersVO mvo) {
        return authService.authenticate(mvo);
    }
    
    @GetMapping("/guest")
    public List<GuestVO> getGuestList() {
        return guestService.getGuestList();
    }

    @GetMapping("/admin")
    public List<MembersVO> getAdminList() {
        return adminService.getAdminList();
    }
}
