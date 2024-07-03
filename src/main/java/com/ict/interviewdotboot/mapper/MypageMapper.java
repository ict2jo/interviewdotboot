package com.ict.interviewdotboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.interviewdotboot.vo.CalendarVO;
import com.ict.interviewdotboot.vo.MyinquiryVO;
import com.ict.interviewdotboot.vo.MyuserVO;

@Mapper
public interface MypageMapper {
    List<MyinquiryVO> getinquiry(String id);
    List<MyinquiryVO> getInquiryDetail(String i_idx);
    int insertinquiry(MyinquiryVO myinquiryVO);
    int editinquiry(MyinquiryVO myinquiryVO);
    int deleteinquiry(String i_idx);
    List<MyuserVO> getprofile(MyuserVO myuserVO);
    int insertprofile(String u_idx);
    int editprofile1(MyuserVO myuserVO);
    int editprofile2(MyuserVO myuserVO);
    int addfavorites(MyuserVO myuserVO);
    int deletefavorites(MyuserVO myuserVO);
    List<MyuserVO> getstar(String u_idx);
    List<CalendarVO> selectCalendar(CalendarVO calendarVO);
    int insertCalendar(CalendarVO calendarVO);
    int updateCalendar(CalendarVO calendarVO);
    int deleteCalendar(CalendarVO calendarVO);
}
