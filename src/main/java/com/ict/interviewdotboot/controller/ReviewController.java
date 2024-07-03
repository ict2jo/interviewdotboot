package com.ict.interviewdotboot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.interviewdotboot.service.ReportService;
import com.ict.interviewdotboot.service.ReviewService;
import com.ict.interviewdotboot.vo.CommentVO;
import com.ict.interviewdotboot.vo.ReportVO;
import com.ict.interviewdotboot.vo.ReviewVO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

     @GetMapping("/reviewlist")
    public List<ReviewVO> getReviewList() {
        return reviewService.getReviewList();
    }

    @PostMapping("/reviewdetail")
    public List<ReviewVO> getReviewDetail() {
        return reviewService.getReviewDetail();
    }

    
    

   /*  @PostMapping("/reviewwrite")
    public int insertReview(@RequestBody ReviewVO reviewVO) {
        System.out.println("reviewVO : " + reviewVO);
        return reviewService.insertReview(reviewVO);
    } */

    @PostMapping("/reviewwrite")
    public ResponseEntity<String> insertReview(@RequestBody ReviewVO reviewVO) {
        boolean res = reviewService.insertReview(reviewVO);
        System.out.println("res : " + res);
        if(res){
            return ResponseEntity.ok("리뷰 작성 : Okay");
        }else{
            return ResponseEntity.status(401).body("NO");
        }
    }
    
    

    @PostMapping("/updatereview")
    public int updateReview(@RequestBody ReviewVO reviewVO) {
        return reviewService.updateReview(reviewVO);
    }

    @PostMapping("/deletereview")
    public int deleteReview(@RequestBody ReviewVO reviewVO) {
        reviewVO.setActive("1");
        return reviewService.deleteReview(reviewVO);
    }

    
    /* @GetMapping("/comments")
    public List<CommentVO> getComments(@RequestParam("r_idx") String r_idx) {
        return reviewService.getComments(r_idx);
    } */

    
    
}
