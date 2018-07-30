package com.widestar.o2o.entity;

public class PersonInfo {
  private Long userId;
  private String name;
  private String profileImg;
  private String email;
  private String gender;
  private Integer enableStatus;
  //1.孤苦 2.店家 3.超级管理员
  private Integer userType;
  private String createTime;
  private String lastEditTime;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getProfileImg() {
    return profileImg;
  }

  public void setProfileImg(String profileImg) {
    this.profileImg = profileImg;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Integer getEnableStatus() {
    return enableStatus;
  }

  public void setEnableStatus(Integer enableStatus) {
    this.enableStatus = enableStatus;
  }

  public Integer getUserType() {
    return userType;
  }

  public void setUserType(Integer userType) {
    this.userType = userType;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getLastEditTime() {
    return lastEditTime;
  }

  public void setLastEditTime(String lastEditTime) {
    this.lastEditTime = lastEditTime;
  }
}
