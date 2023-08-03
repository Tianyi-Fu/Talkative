package com.demo.serivce;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.mapper.FeedBackRecordAndUserInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    FeedBackRecordAndUserInfoMapper feedBackRecordAndUserInfoMapper;

    /**
     * select Feedback Datasheet list
     * @param chatRecordId
     * @param page
     * @param size
     * @return
     */
    public Object[] selectFeedBackRecordAndUserInfo(String chatRecordId, int page, int size) {
        IPage<Map<String, Object>> iPage = new Page<>(page, size);
        feedBackRecordAndUserInfoMapper.selectFeedBackRecordAndUserInfo(iPage, chatRecordId);
        return new Object[]{iPage.getRecords(), iPage.getTotal()};
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
}
