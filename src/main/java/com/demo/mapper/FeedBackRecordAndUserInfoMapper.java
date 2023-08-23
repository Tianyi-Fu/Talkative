package com.demo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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


    //    @Select("<script>" +
//            "select distinct(dd.feedback_record_id),dd.chat_record_id,dd.agent_name,dd.transcript, dd.first_name,  dd.last_name,dd.email,dd.created_at,fr.answer from feedback_record as fr," +
//            "        (" +
//            "        select feedback_record_id,cc.chat_record_id,cc.agent_name,cc.transcript, f.first_name," +
//            "        f.last_name,f.email,f.created_at from feedback_user_info f,chat_record as cc" +
//            "        where cc.chat_record_id = f.chat_record_id" +
//            "        <if test=\"chat_record_id != null and chat_record_id != ''\">" +
//            "            and f.chat_record_id = #{chat_record_id}" +
//            "        </if>" +
//            "        ) as dd where fr.feedback_record_id = dd.feedback_record_id and fr.question_id = 1" +
//            "        order by dd.created_at desc" +
//            "</script>")
    @Select("<script>" +
            "select distinct(dd.feedback_record_id),dd.chat_record_id,dd.agent_name,dd.transcript, dd.first_name," +
            "        dd.last_name,dd.email,dd.created_at,fr.answer from feedback_record as fr," +
            "        (" +
            "        select feedback_record_id,cc.chat_record_id,cc.agent_name,cc.transcript, f.first_name," +
            "        f.last_name,f.email,f.created_at from feedback_user_info f,chat_record as cc" +
            "        where cc.chat_record_id = f.chat_record_id" +
            "        <if test=\"chat_record_id != null and chat_record_id != ''\">" +
            "            and f.chat_record_id = #{chat_record_id}" +
            "        </if>" +
            "        ) as dd where fr.feedback_record_id = dd.feedback_record_id and fr.question_id = 1" +
            "        <if test=\"satisfaction != null and satisfaction != ''\">" +
            "            and fr.answer = #{satisfaction}" +
            "        </if>" +
            "        <if test=\"agentName != null and agentName != ''\">" +
            "            and dd.agent_name = #{agentName}" +
            "        </if>" +
            "        <if test=\"beginTime != null and beginTime != '' and endTime != null and endTime !=''\">" +
            "            and dd.created_at between #{beginTime} and #{endTime}" +
            "        </if>" +
            "        order by dd.created_at desc" +
            "</script>")
    IPage<Map<String, Object>> selectFeedBackRecordAndUserInfo(IPage<Map<String, Object>> iPage, @Param("chat_record_id") String chatRecordId,
                                                               @Param("satisfaction") String satisfaction, @Param("agentName") String agentName,
                                                               @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    @Select("<script>" +
            "select distinct(dd.feedback_record_id),dd.chat_record_id,dd.agent_name,dd.transcript, dd.first_name," +
            "        dd.last_name,dd.email,dd.created_at,fr.answer from feedback_record as fr," +
            "        (" +
            "        select feedback_record_id,cc.chat_record_id,cc.agent_name,cc.transcript, f.first_name," +
            "        f.last_name,f.email,f.created_at from feedback_user_info f,chat_record as cc" +
            "        where cc.chat_record_id = f.chat_record_id" +
            "        ) as dd where fr.feedback_record_id = dd.feedback_record_id and fr.question_id = 1" +
            "        order by dd.created_at desc" +
            "</script>")
    List<Map<String,Object>> selectFeedBackRecordAndUserInfoExcel();

    @Select("select question_id,question,answer from feedback_record where feedback_record_id = #{feedbackRecordId}")
    List<Map<String, Object>> selectFeedBackRecordByRecordId(String feedbackRecordId);

    @Select("select chat_record_id,transcript from chat_record where feedback_record_id = #{feedbackRecordId}")
    Map<String, Object> selectChatRecordByRecordId(String feedbackRecordId);

    @Select("select count(1) from chat_record")
    int selectCountCharRecord();



    @Select("<script>" +
            "select count(1) from feedback_record where question_id = 1\n" +
            "        <if test=\"type == 1 \">\n" +
            "            and (answer = 1 or answer = 2)\n" +
            "        </if>\n" +
            "        <if test=\"type == 2 \">\n" +
            "            and (answer = 3 or answer = 4)\n" +
            "        </if>\n" +
            "        <if test=\"type == 3 \">\n" +
            "            and (answer = 5)\n" +
            "        </if>" +
            "</script>")
    int selectAnswerCountByQuestionId1(int type);

    //2
    @Delete("delete from chat_record where feedback_record_id = #{feedbackRecordId}")
    int deleteChatRecord(int feedbackRecordId);

    //1
    @Delete("delete from feedback_record where feedback_record_id = #{feedbackRecordId}")
    int deleteChatRecordFeedbackRecord(int feedbackRecordId);

    //3
    @Delete("delete from feedback_user_info where chat_record_id = #{chatRecordId}")
    int deleteChatRecordFeedbackUserInfo(int chatRecordId);

    @Update("update feedback_user_info set first_name = #{first_name},last_name=#{last_name},email=#{email},agent_name=#{agent_name} where chat_record_id = #{chat_record_id}")
    int updFeedBackRecordByRecordId(@Param("chat_record_id") int chatRecordId, @Param("first_name") String firstName,
                                    @Param("last_name") String lastName, @Param("email") String email, @Param("agent_name") String agentName);

    @Update("update chat_record set agent_name = #{agent_name} where feedback_record_id = #{feedback_record_id}")
    int updChatRecordByRecordId(@Param("feedback_record_id") String feedbackRecordId,@Param("agent_name") String agentName);
}
