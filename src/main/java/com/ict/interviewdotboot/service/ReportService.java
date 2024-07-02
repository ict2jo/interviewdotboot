package com.ict.interviewdotboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.interviewdotboot.mapper.ReportMapper;
import com.ict.interviewdotboot.vo.ReportVO;
import java.util.List;

@Service
public class ReportService {
    
    @Autowired
    private ReportMapper reportMapper;

    public List<ReportVO> getReportList(){
        return reportMapper.getReportList();
    }

    public boolean insertReport(ReportVO reportVO){
        int res = reportMapper.insertReport(reportVO);
        return res > 0;
    }

    public int updateReport(ReportVO reportVO){
        return reportMapper.updateReport(reportVO);
    }
}
