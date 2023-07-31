package com.qw.pojo.bo.center;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

public class CenterUserBO {
    private String username;
    @NotBlank(message = "user real name cannot be empty")
    @Length(max = 12, message = "user real name cannot be longer than 12 chars")
    private String realname;

    @NotBlank(message = "user nickname cannot be empty")
    @Length(max = 12, message = "user nickname cannot be longer than 12 chars")
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    @Pattern(regexp = "^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$")
    private String mobile;
    @Email
    private String email;

    private Date birthday;

    @Min(value = 0, message = "gender selection not correct")
    @Max(value = 2, message = "gender selection not correct")
    private Integer sex;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
