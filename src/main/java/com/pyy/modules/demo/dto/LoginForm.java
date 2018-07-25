package com.pyy.modules.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 登录表单
 * @author: pyygithub
 * @date: 2018-07-22 10:54
 * @version: v1.0
 */
@ApiModel(value = "LoginForm", description="登录表单")
@Data
public class LoginForm {
    @ApiModelProperty(value = "手机号")
    @NotBlank(message="手机号不能为空")
    private String mobile;

    @ApiModelProperty(value = "密码")
    @NotBlank(message="密码不能为空")
    private String password;

}
