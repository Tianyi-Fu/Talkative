package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.model.FeedbackAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @ClassName: FeedbackAnalysisMapper
 * @author: Wei Peng
 * @date: 2023-08-18 14:04
 * @version: 1.0
 * @Description:
 */


public interface FeedbackAnalysisMapper extends BaseMapper<FeedbackAnalysis> {

    @Select("select suggestion from feedback_analysis where feedback_record_id = #{feedbackRecordId}")
    String selectAnalysis(String feedbackRecordId);
}
