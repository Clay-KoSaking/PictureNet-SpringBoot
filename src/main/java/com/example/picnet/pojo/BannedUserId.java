package com.example.picnet.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
public class BannedUserId implements Serializable {
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Column(name = "banned_time", nullable = false)
    private java.sql.Timestamp bannedTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getBannedTime() {
        return bannedTime;
    }

    public void setBannedTime(Timestamp bannedTime) {
        this.bannedTime = bannedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BannedUserId that)) return false;

        if (!getUserId().equals(that.getUserId())) return false;
        return getBannedTime().equals(that.getBannedTime());
    }

    @Override
    public int hashCode() {
        int result = getUserId().hashCode();
        result = 31 * result + getBannedTime().hashCode();
        return result;
    }
}
