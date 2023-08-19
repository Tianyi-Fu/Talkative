package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.model.ChatRecord;
import com.demo.model.FeedbackRecord;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Map;

/**
 * @author Tianyi Fu
 * @since 2023/7/20
 */
@EnableCaching
public interface ChatRecordMapper extends BaseMapper<ChatRecord> {

    @Select("select count(*) total from chat_record")
    Map<String, Object> gelTotal();
}