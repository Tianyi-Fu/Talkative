package com.demo.serivce;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.data.DemoData;
import com.demo.error.DemoException;
import com.demo.mapper.DemoMapper;
import com.demo.model.Demo;
import com.demo.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tianyi Fu
 * @since 2023/7/5
 */
@Service
@RequiredArgsConstructor
public class DemoService {


    private final DemoMapper demoMapper;

    public List<DemoData.Vo> queryAll() throws DemoException {
        List<Demo> demos = demoMapper.selectList(new QueryWrapper<>());
        return BeanUtil.convertToList(demos,DemoData.Vo.class);
    }

    public DemoData.Vo query(DemoData.QueryParam query) throws DemoException {
        LambdaQueryWrapper<Demo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Demo::getContent, query.getContent());
        Demo demo = demoMapper.selectOne(wrapper);
        return BeanUtil.sourceToTarget(demo, DemoData.Vo.class);
    }

    public void create(DemoData.SaveParam param) throws DemoException {
        Demo demo = BeanUtil.sourceToTarget(param, Demo.class);
        demoMapper.insert(demo);
    }

    public void update(DemoData.SaveParam param) throws DemoException {
        Demo demo = BeanUtil.sourceToTarget(param, Demo.class);
        demoMapper.updateById(demo);
    }

    public void delete(Long id) {
        demoMapper.deleteById(id);
    }

}
