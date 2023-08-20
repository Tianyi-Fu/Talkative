package com.demo.serivce;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.mapper.ReviewAnalysisMapper;
import lombok.RequiredArgsConstructor;
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
public class ReviewAnalysisService {

    @Resource
    ReviewAnalysisMapper reviewAnalysisMapper;

    /**
     * select Feedback Datasheet list
     *
     * @param chatRecordId
     * @param page
     * @param size
     * @return
     */
    public Object[] selectPageList(String chatRecordId, int page, int size) {
        IPage<Map<String, Object>> iPage = new Page<>(page, size);
        reviewAnalysisMapper.selectPageList(iPage, chatRecordId);
        return new Object[]{iPage.getRecords(), iPage.getTotal(), iPage.getPages(), iPage.getSize()};
    }

    public List<Map<String, Object>> selectSentimentNtlk() {
        return reviewAnalysisMapper.selectSentimentNtlk();
    }
    public List<Map<String, Object>> selectSentimentFlair() {
        return reviewAnalysisMapper.selectSentimentFlair();
    }
    public List<Map<String, Object>> selectSentimentBert() {
        return reviewAnalysisMapper.selectSentimentBert();
    }

    public Map<String, Object> getTopic() {
        return reviewAnalysisMapper.getTopic();
    }

}
