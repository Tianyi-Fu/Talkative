package com.demo.serivce;

import com.demo.error.DemoException;
import com.demo.mapper.TranscriptMapper;
import com.demo.model.Chat_record;
import com.demo.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TranscriptService {
    private final TranscriptMapper transcriptMapper;

    public void create(String param) throws DemoException {
        System.out.println(param);

        Chat_record transcript = BeanUtil.sourceToTarget(param, Chat_record.class);

        System.out.println(transcript);
        transcriptMapper.insert(transcript);
    }
}
