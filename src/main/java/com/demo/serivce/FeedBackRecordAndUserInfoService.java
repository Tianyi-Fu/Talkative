package com.demo.serivce;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.mapper.FeedBackRecordAndUserInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: FeedBackRecordAndUserInfoService
 * @author: Wei Peng
 * @date: 2023-08-02 22:07
 * @version: 1.0
 * @Description:
 */

@Service
@RequiredArgsConstructor
public class FeedBackRecordAndUserInfoService {

    @Resource
    FeedBackRecordAndUserInfoMapper feedBackRecordAndUserInfoMapper;

    /**
     * select Feedback Datasheet list
     *
     * @param chatRecordId
     * @param page
     * @param size
     * @return
     */
    public Object[] selectFeedBackRecordAndUserInfo(String chatRecordId, int page, int size, String satisfaction, String agentName, String beginTime, String endTime) {
        IPage<Map<String, Object>> iPage = new Page<>(page, size);
        feedBackRecordAndUserInfoMapper.selectFeedBackRecordAndUserInfo(iPage, chatRecordId, satisfaction, agentName, beginTime, endTime);
        return new Object[]{iPage.getRecords(), iPage.getTotal(), iPage.getPages(), iPage.getSize()};
    }

    public List<Map<String,Object>> selectFeedBackRecordAndUserInfoExcel(){
        return feedBackRecordAndUserInfoMapper.selectFeedBackRecordAndUserInfoExcel();
    }

    public List<Map<String, Object>> selectFeedBackRecordByRecordId(String feedbackRecordId) {
        if (StringUtils.isBlank(feedbackRecordId)) {
            return null;
        }
        return feedBackRecordAndUserInfoMapper.selectFeedBackRecordByRecordId(feedbackRecordId);
    }

    public Map<String, Object> selectChatRecordByRecordId(String feedbackRecordId) {
        if (StringUtils.isBlank(feedbackRecordId)) {
            return null;
        }
        return feedBackRecordAndUserInfoMapper.selectChatRecordByRecordId(feedbackRecordId);
    }

    public void del(int feedbackRecordId) {
        Map<String, Object> map = feedBackRecordAndUserInfoMapper.selectChatRecordByRecordId(String.valueOf(feedbackRecordId));
        int chatRecordId = (int) map.get("chat_record_id");
        feedBackRecordAndUserInfoMapper.deleteChatRecordFeedbackRecord(feedbackRecordId);
        feedBackRecordAndUserInfoMapper.deleteChatRecord(feedbackRecordId);
        feedBackRecordAndUserInfoMapper.deleteChatRecordFeedbackUserInfo(chatRecordId);
    }

    public void upd(String feedbackRecordId, String firstName, String lastName, String email, String agentName) {
        Map<String, Object> map = feedBackRecordAndUserInfoMapper.selectChatRecordByRecordId(String.valueOf(feedbackRecordId));
        int chatRecordId = (int) map.get("chat_record_id");
        feedBackRecordAndUserInfoMapper.updFeedBackRecordByRecordId(chatRecordId,firstName,lastName,email,agentName);
        feedBackRecordAndUserInfoMapper.updChatRecordByRecordId(feedbackRecordId,agentName);

    }
}
