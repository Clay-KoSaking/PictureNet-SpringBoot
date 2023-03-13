package com.example.picnet.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "banneduser")
@IdClass(BannedUserId.class)
public class BannedUser {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Id
    @Column(name = "banned_time", nullable = false)
    private java.sql.Timestamp bannedTime;
    @Column(name = "banned_reason", nullable = false)
    private String bannedReason;
    @Column(name = "is_banned", nullable = false)
    private Integer isBanned;


    public Integer getUserId() {
      return userId;
    }

    public void setUserId(Integer userId) {
      this.userId = userId;
    }


    public java.sql.Timestamp getBannedTime() {
      return bannedTime;
    }

    public void setBannedTime(java.sql.Timestamp bannedTime) {
      this.bannedTime = bannedTime;
    }


    public String getBannedReason() {
      return bannedReason;
    }

    public void setBannedReason(String bannedReason) {
      this.bannedReason = bannedReason;
    }

    public Integer getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(Integer isBanned) {
        this.isBanned = isBanned;
    }

    public BannedUser() {
    }
}
