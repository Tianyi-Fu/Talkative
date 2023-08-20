package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.model.ChatRecord;
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
public interface ReviewAnalysisMapper extends BaseMapper<ChatRecord> {

    @Select("<script>" +
            "select id,review_text,sentiment_ntlk,sentiment_flair,sentiment_bert,topic,chat_record_id from review_analysis r" +
            "        where 1=1 " +
            "        <if test=\"chat_record_id != null and chat_record_id != ''\">" +
            "            and r.chat_record_id = #{chat_record_id}" +
            "        </if>" +
            "        order by r.id asc" +
            "</script>")
    IPage<Map<String, Object>> selectPageList(IPage<Map<String, Object>> iPage, @Param("chat_record_id") String chatRecordId);

    @Select("select ifnull(sum(Negative),0) Negative,ifnull(sum(Neutral),0) Neutral,ifnull(sum(Positive),0) Positive from ( select type," +
            "case when UPPER(type)='NEGATIVE' then sum(cou) end as Negative," +
            "case when UPPER(type)='NEUTRAL' then sum(cou) end as Neutral," +
            "case when UPPER(type)='POSITIVE' then sum(cou) end as Positive " +
            "from (select sentiment_ntlk type,count(*)cou from review_analysis group by sentiment_ntlk) t  group by type)c ")
    List<Map<String, Object>> selectSentimentNtlk();

    @Select("select ifnull(sum(Negative),0) Negative,ifnull(sum(Neutral),0) Neutral,ifnull(sum(Positive),0) Positive from ( select type," +
            "case when UPPER(type)='NEGATIVE' then sum(cou) end as Negative," +
            "case when UPPER(type)='NEUTRAL' then sum(cou) end as Neutral," +
            "case when UPPER(type)='POSITIVE' then sum(cou) end as Positive " +
            "from (select sentiment_flair type,count(*)cou from review_analysis group by sentiment_flair) t  group by type)c ")
    List<Map<String, Object>> selectSentimentFlair();

    @Select("select ifnull(sum(Negative),0) Negative,ifnull(sum(Neutral),0) Neutral,ifnull(sum(Positive),0) Positive from ( select type," +
            "case when UPPER(type)='NEGATIVE' then sum(cou) end as Negative," +
            "case when UPPER(type)='NEUTRAL' then sum(cou) end as Neutral," +
            "case when UPPER(type)='POSITIVE' then sum(cou) end as Positive " +
            "from (select sentiment_bert type,count(*)cou from review_analysis group by sentiment_bert) t  group by type)c ")
    List<Map<String, Object>> selectSentimentBert();

    @Select("select (select count(*)cou From review_analysis where topic like '%thank (%')thank," +
            "(select count(*)cou From review_analysis where topic like '%chat (%')chat," +
            "(select count(*)cou From review_analysis where topic like '%agent (%')agent," +
            "(select count(*)cou From review_analysis where topic like '%steve (%')steve," +
            "(select count(*)cou From review_analysis where topic like '%need (%')need," +
            "(select count(*)cou From review_analysis where topic like '%work (%')work," +
            "(select count(*)cou From review_analysis where topic like '%talk (%')talk," +
            "(select count(*)cou From review_analysis where topic like '%ye (%')ye," +
            "(select count(*)cou From review_analysis where topic like '%script (%')script," +
            "(select count(*)cou From review_analysis where topic like '%felix (%')felix," +
            "(select count(*)cou From review_analysis where topic like '%want (%')want," +
            "(select count(*)cou From review_analysis where topic like '%integr (%')integr," +
            "(select count(*)cou From review_analysis where topic like '%great (%')great," +
            "(select count(*)cou From review_analysis where topic like '%compani (%')compani," +
            "(select count(*)cou From review_analysis where topic like '%que (%')que," +
            "(select count(*)cou From review_analysis where topic like '%tener (%')tener," +
            "(select count(*)cou From review_analysis where topic like '%good (%')good," +
            "(select count(*)cou From review_analysis where topic like '%time (%')time," +
            "(select count(*)cou From review_analysis where topic like '%appl (%')appl," +
            "(select count(*)cou From review_analysis where topic like '%widget (%')widget," +
            "(select count(*)cou From review_analysis where topic like '%code (%')code," +
            "(select count(*)cou From review_analysis where topic like '%page (%')page," +
            "(select count(*)cou From review_analysis where topic like '%ignit (%')ignit," +
            "(select count(*)cou From review_analysis where topic like '%client (%')client," +
            "(select count(*)cou From review_analysis where topic like '%access (%')access," +
            "(select count(*)cou From review_analysis where topic like '%avail (%')avail," +
            "(select count(*)cou From review_analysis where topic like '%trial (%')trial," +
            "(select count(*)cou From review_analysis where topic like '%free (%')free," +
            "(select count(*)cou From review_analysis where topic like '%app (%')app," +
            "(select count(*)cou From review_analysis where topic like '%video (%')video," +
            "(select count(*)cou From review_analysis where topic like '%sie (%')sie," +
            "(select count(*)cou From review_analysis where topic like '%html (%')html," +
            "(select count(*)cou From review_analysis where topic like '%copi (%')copi," +
            "(select count(*)cou From review_analysis where topic like '%user (%')user," +
            "(select count(*)cou From review_analysis where topic like '%bot (%')bot from dual")
    Map<String, Object> getTopic();
}
