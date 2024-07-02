package com.ict.interviewdotboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.interviewdotboot.mapper.ReportSuccMapper;
import com.ict.interviewdotboot.vo.ReportVO;

import java.util.List;

@Service
public class ReportSuccService {
    
    @Autowired
    private ReportSuccMapper reportSuccMapper;

    public List<ReportVO> getReportSuccList(){
        return reportSuccMapper.getReportSuccList();
    }

    public boolean insertSuccReport(ReportVO reportVO){
        int res = reportSuccMapper.insertSuccReport(reportVO);
        return res > 0;
    }

    public int updateSuccReport(ReportVO reportVO){
        return reportSuccMapper.updateSuccReport(reportVO);
    }
}
