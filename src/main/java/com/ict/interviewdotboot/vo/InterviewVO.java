package com.ict.interviewdotboot.vo;

import lombok.Data;

@Data
public class InterviewVO {
    private String category; // 직군별 종류
    private String question; // 질문
    private String q_idx; // 질문 idx
    private String id; //유저 아이디
    private String re_idx; // Interview_re 의 idx ( 결과 idx)
    private String r_idx;
    private String text; // STT => 텍스트 결과
    private String pose_results; // 어깨 움직임 횟수
    private String sentiment; // 감정분석
    private String interview_date; // 인터뷰 한 일자
    private String video_uuid; // 영상 파일이름 저장
    private String intention; // 해당 질문에 대한 질문의도
    private String feedback; // 질문 피드백
    private String campus; // TEXT를 교정해준 문구
    private String selectedIds;
    private int payCount;
    private String search;
}
