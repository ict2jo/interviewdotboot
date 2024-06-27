package com.ict.interviewdotboot.config;

import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

@Service
public class CustomOAuth2userService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String provider = userRequest.getClientRegistration().getRegistrationId();

        if ("naver".equals(provider)) {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");
            if (response == null) {
                throw new OAuth2AuthenticationException("Naver error");
            }
            String name = (String) response.get("name");
            String email = (String) response.get("email");
            String phone = (String) response.get("mobile");
            return new DefaultOAuth2User(oAuth2User.getAuthorities(), Map.of(
                "email", email,
                "name", name,
                "id", response.get("id"),
                "phone", phone
            ), "email");
        } else if ("kakao".equals(provider)) {
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            if (kakaoAccount == null) {
                throw new OAuth2AuthenticationException("Kakao error");
            }
            String email = (String) kakaoAccount.get("email");
            Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
            if (properties == null) {
                throw new OAuth2AuthenticationException("Kakao error2");
            }
            String name = (String) properties.get("nickname");
            return new DefaultOAuth2User(oAuth2User.getAuthorities(), Map.of(
                "email", email,
                "name", name,
                "id", attributes.get("id")
            ), "email");
        } else if ("google".equals(provider)) {
            String email = (String) attributes.get("email");
            String name = (String) attributes.get("name");
            return new DefaultOAuth2User(oAuth2User.getAuthorities(), Map.of(
                "email", email,
                "name", name,
                "id", attributes.get("sub")
            ), "email");
        }

        return oAuth2User;
    }
}
