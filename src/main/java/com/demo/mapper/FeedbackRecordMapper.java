package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.model.ChatRecord;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @ClassName: FeedBackRecordAndUserInfoMapper
 * @author: Wei Peng
 * @date: 2023-08-02 21:56
 * @version: 1.0
 * @Description:
 */
public interface FeedbackRecordMapper extends BaseMapper<ChatRecord> {

    @Select("select count(*) total from feedback_record")
    Map<String, Object> gelTotal();

}
