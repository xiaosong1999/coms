package com.dfbz.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@Accessors
public class RegisterAdminVo implements Serializable {

    @NotBlank(message = "管理员名字不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^\\w{6,20}$",message = "密码是6到20位字母数字_")
    private String password;
    @NotBlank(message = "昵称不能为空")
    private String name;

    private Integer type;


}
