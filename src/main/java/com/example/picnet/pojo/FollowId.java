package com.example.picnet.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
public class FollowId implements Serializable {
    @Column(name = "follow_user_id", nullable = false)
    private Integer followUserId;
    @Column(name = "follow_time", nullable = false)
    private java.sql.Timestamp followTime;

    public Integer getFollowUserId() {
        return followUserId;
    }

    public void setFollowUserId(Integer followUserId) {
        this.followUserId = followUserId;
    }

    public Timestamp getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Timestamp followTime) {
        this.followTime = followTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FollowId followId)) return false;

        if (!getFollowUserId().equals(followId.getFollowUserId())) return false;
        return getFollowTime().equals(followId.getFollowTime());
    }

    @Override
    public int hashCode() {
        int result = getFollowUserId().hashCode();
        result = 31 * result + getFollowTime().hashCode();
        return result;
    }
}
