package com.ict.interviewdotboot.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ict.interviewdotboot.jwt.JWTUtil;
import com.ict.interviewdotboot.service.MyUserDetailsService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


// OAth2 로그인 성공시 처리하는 핸들러 
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
    private final com.ict.interviewdotboot.jwt.JWTUtil jwtUtil;
    private final MyUserDetailsService userDetailsService;
    
    public OAuth2AuthenticationSuccessHandler(JWTUtil jwtUtil, MyUserDetailsService userDetailsService){
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        try {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            String provoider = getProviderFormRequest(request);

            // 디버깅 로그 
            System.out.println("OAuth2 User : " + oAuth2User);
            System.out.println("Attributes: " + oAuth2User.getAttributes());
            System.out.println("Authorites : " + oAuth2User.getAuthorities());

            // 성공 후 토큰 가지고 클라이언트로 넘어간다. 
            UserDetails userDetails = userDetailsService.loadUserByOAuth2User(oAuth2User, provoider);
            String token = jwtUtil.generateToken(userDetails);
            System.out.println("token: "+token);
            response.addHeader("Authorization", "Bearer " +token);            
            response.sendRedirect("http://localhost:3000/signin/login?token="+token);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/login?error");
        }
        
    }

    public String getProviderFormRequest (HttpServletRequest request){
        String uri = request.getRequestURI();
        if(uri.contains("kakao")){
            return "kakao";
        }else if(uri.contains("naver")){
            return "naver";
        }else if(uri.contains("google")){
            return "google";
        }else{
            return "unknown";
        }
    }
}






