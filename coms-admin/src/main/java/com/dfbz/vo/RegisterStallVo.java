package com.dfbz.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@Accessors
public class RegisterStallVo implements Serializable {

    @NotBlank(message = "采购商名字不能为空")
    private String name;
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^\\w{6,20}$",message = "密码是6到20位字母数字_")
    private String password;
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1\\d{10}$",message = "手机号格式不正确")
    private String phone;

    private Integer status;

}
