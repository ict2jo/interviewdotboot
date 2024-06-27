package com.ict.interviewdotboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.mail.javamail.JavaMailSender;

@Service
public class MailService {
  @Autowired
  private JavaMailSender mailSender;
	
	// Controller에서 호출
	public void sendEmail(String randomNumber, String toMail) {
		try {
			MailHandler sendMail = new MailHandler(mailSender);
			sendMail.setSubject("[AIM 인증 메일입니다]");
			
			sendMail.setText("<div style='display: flex; flex-direction: column; align-items: center;'>"
				+ "<h1>AIM 메일 인증</h1>"
				+ "<h1>------------------------</h1>"
				+ "<p style='font-size: 30px;'>인증번호 : " + randomNumber + "</p>"
				+ "</div>");
			
			sendMail.setFrom("jinewholic@gmail.com", "InterviewDot");
			
			sendMail.setTo(toMail);
			sendMail.send();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//아이디 찾기
	public void sendMyIDEmail(String userId, String toMail) {
		try {
			MailHandler sendMail = new MailHandler(mailSender);
			// 메일 제목
			sendMail.setSubject("[CAMPYOU 인증 메일입니다]");
			
			// 메일 내용
			// 내용
			sendMail.setText("<table><tbody>"
					+ "<tr><td><h2>CAMPYOU 메일 인증</h2></td></tr>"
					+ "<tr><td><h3>CAMPYOU</h3></td></tr>"
					+ "<tr><td><font size='5px'>아이디 안내입니다</font></td></tr>"
					+ "<tr><td><font size='8px'>확인 아이디 : "+userId +"</font></td></tr>"
					+ "</tbody></table>");
			
			// 보내는 이
			sendMail.setFrom1("interviewdot@gmail.com", "interviewdot");
			
			// 받는 이
			sendMail.setTo1(toMail);
			sendMail.send();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
