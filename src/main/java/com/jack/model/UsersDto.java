package com.jack.model;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

public class UsersDto {

    @NotBlank(message = "不得為空")
    private String userName;
    @Length(min = 6, message = "至少需6位")
    private String password;
    private String confirmPassword;
    @Pattern(regexp = "09[0-9]{8}", message = "格式錯誤")
    private String phone;
    @Email(message = "格式錯誤")
    private String email;


    public boolean ValidPassword(){
        return this.password.equals(confirmPassword);
    }
    /**
     * 轉換為Users
     * @return
     */
    public Users ConvertToUser(){
        Users user = new Users();
        user.setUserTypeId(2);
        BeanUtils.copyProperties(this, user);

        return user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
