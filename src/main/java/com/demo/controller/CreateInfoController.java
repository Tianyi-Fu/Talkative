package com.demo.controller;

import com.demo.error.Exception;
import com.demo.model.CreateUserInfo;
import com.demo.model.Result;
import com.demo.serivce.CreateInfoService;
import com.demo.serivce.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: CreateInfoController
 * @Description:
 * @author: Wei Peng
 * @date: 2023-07-24 23:41
 * @version: 1.0
 */

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin
public class CreateInfoController {

    private final CreateInfoService service;

    /**
     * Create user info
     */
    @PostMapping("/create-info/{agentName}")
    public Result createInfo(@RequestBody CreateUserInfo createUserInfo, @PathVariable String agentName) throws Exception {
        System.out.println(createUserInfo);
        service.createInfo(createUserInfo.getArray(), createUserInfo.getChatRecordIdList(), agentName,
                createUserInfo.getFirstName(), createUserInfo.getLastName(), createUserInfo.getEmail(),
                createUserInfo.getChatRecordId());
        return Result.success();
    }
}
