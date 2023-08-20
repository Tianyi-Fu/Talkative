package com.demo.serivce;



import com.demo.mapper.FeedbackRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class FeedbackRecordService {

    @Resource
    FeedbackRecordMapper feedbackRecordMapper;


    public Map<String, Object> gelTotal() {
        return feedbackRecordMapper.gelTotal();
    }
}
