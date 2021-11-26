package com.dfbz.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Data
@Accessors
public class RegisterSupVo implements Serializable {

    @NotBlank(message = "采购商名字不能为空")
    private String name;
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^\\w{6,20}$",message = "密码是6到20位字母数字_")
    private String password;
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1\\d{10}$",message = "手机号格式不正确")
    private String phone;
    @NotEmpty(message = "分类不能为空")
    private List<String> category;
    @NotBlank(message = "地址不能为空")
    private String address;

    private Integer status;
}
