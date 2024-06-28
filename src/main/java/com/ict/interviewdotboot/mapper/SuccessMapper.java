package com.ict.interviewdotboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.interviewdotboot.vo.SuccessVO;

@Mapper
public interface SuccessMapper {
    List<SuccessVO> getSuccessList();
    List<SuccessVO> getSuccessDetail();
    int insertSuccess(SuccessVO successVO);
    int updateSuccess(SuccessVO successVO);
    int deleteSuccess(SuccessVO successVO);
}
