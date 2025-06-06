package com.cyworld.cyworld_clone.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserDto {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String birthday;
    private String email;
    private String phone;
    private String gender;
    private Integer profileImgId;
    private LocalDateTime createdAt;

    public LocalDate getBirthDayAsLocalDate() {
        if (birthday == null || birthday.isEmpty()) return null;

        return LocalDate.parse(birthday);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getProfileImgId() {
        return profileImgId;
    }

    public void setProfileImgId(Integer profileImgId) {
        this.profileImgId = profileImgId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}



