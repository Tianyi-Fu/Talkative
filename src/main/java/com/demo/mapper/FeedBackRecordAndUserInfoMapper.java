package com.demo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: FeedBackRecordAndUserInfoMapper
 * @author: Wei Peng
 * @date: 2023-08-02 21:56
 * @version: 1.0
 * @Description:
 */
public interface FeedBackRecordAndUserInfoMapper {


    @Select("<script>" +
            "select dd.*,fr.answer from feedback_record as fr," +
            "        (" +
            "        select distinct(cc.feedback_record_id),cc.chat_record_id,cc.agent_name,cc.transcript, f.first_name," +
            "        f.last_name,f.email,f.created_at from feedback_user_info f,chat_record as cc" +
            "        where cc.chat_record_id = f.chat_record_id" +
            "        <if test=\"chat_record_id != null and chat_record_id != ''\">" +
            "            and f.chat_record_id = #{chat_record_id}" +
            "        </if>" +
            "        ) as dd where fr.feedback_record_id = dd.feedback_record_id and fr.question_id = 1" +
            "        order by dd.created_at desc" +
            "</script>")
    IPage<Map<String, Object>> selectFeedBackRecordAndUserInfo(IPage<Map<String, Object>> iPage, @Param("chat_record_id") String chatRecordId);

    @Select("select question,answer from feedback_record where feedback_record_id = #{feedbackRecordId}")
    List<Map<String,Object>> selectFeedBackRecordByRecordId(String feedbackRecordId);

    @Select("select transcript from chat_record where feedback_record_id = #{feedbackRecordId}")
    Map<String,Object> selectChatRecordByRecordId(String feedbackRecordId);
}
