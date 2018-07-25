package com.pyy.modules.demo.controller;

import com.alibaba.fastjson.JSON;
import com.pyy.common.enums.ResultEnum;
import com.pyy.modules.demo.dto.LoginForm;
import com.pyy.modules.demo.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 用户Controller类
 * @author: pyygithub
 * @date: 2018-07-22 10:38
 * @version: v1.0
 */
@RestController
@Slf4j
@Api(value="用户controller",description="用户操作",tags={"用户操作接口"})
@RequestMapping("/v1")
public class UserController {

    @ApiOperation(value = "查询员工信息", notes = "查询员工信息", response = ResultVO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "员工id", required = true, dataType = "String", paramType = "path")
    })
    @GetMapping("/user/{id}")
    public ResultVO login(@PathVariable("id") String id) {
        log.info("【用户查询】,userId={}", id);
        return ResultVO.ok();
    }

    @ApiOperation(value = "查询员工信息", notes = "查询员工信息", response = ResultVO.class)
    @PostMapping("/user/login")
    public ResultVO login(@RequestBody LoginForm loginForm) {
        log.info("【用户登录】,loginform={}", JSON.toJSONString(loginForm));
        return new ResultVO(ResultEnum.LOGIN_SUCCESS);
    }
}
