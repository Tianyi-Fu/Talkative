package com.demo.controller;

import com.demo.data.DemoData;
import com.demo.error.DemoException;
import com.demo.model.Result;
import com.demo.serivce.DemoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tianyi Fu
 * @since 2023/7/5
 */

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin
public class DemoController {

    private final DemoService demoService;

    @GetMapping("/test")
    public String test(){
        return "test";
    }


    /**
     * Query all
     */
    @GetMapping("/all")
    public Result<List<DemoData.Vo>> queryAll() throws DemoException {
        List<DemoData.Vo> vo = demoService.queryAll();
        return Result.success(vo);
    }

    /**
     * Query
     */
    @GetMapping("/query")
    public Result<DemoData.Vo> query(DemoData.QueryParam param) throws DemoException {
        if (param.getContent()==null || param.getContent().equals(""))throw new DemoException("No values");
        DemoData.Vo vo = demoService.query(param);
        return Result.success(vo);
    }

    /**
     * Create
     */
    @PostMapping("/create")
    public Result create(@RequestBody DemoData.SaveParam param) throws DemoException {
        demoService.create(param);
        return Result.success();
    }

    /**
     * Update
     */
    @PostMapping("/update")
    public Result update(@RequestBody DemoData.SaveParam param) throws DemoException {
        demoService.update(param);
        return Result.success();
    }

    /**
     * Delete
     */
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){
        demoService.delete(id);
        return Result.success();
    }

}
