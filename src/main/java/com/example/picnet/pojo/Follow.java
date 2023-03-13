package com.example.picnet.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "follow")
@IdClass(FollowId.class)
public class Follow {
    @Id
    @Column(name = "follow_user_id", nullable = false)
    private Integer followUserId;
    @Column(name = "followed_user_id", nullable = false)
    private Integer followedUserId;
    @Id
    @Column(name = "follow_time", nullable = false)
    private java.sql.Timestamp followTime;


    public Integer getFollowUserId() {
      return followUserId;
    }

    public void setFollowUserId(Integer followUserId) {
      this.followUserId = followUserId;
    }


    public Integer getFollowedUserId() {
      return followedUserId;
    }

    public void setFollowedUserId(Integer followedUserId) {
      this.followedUserId = followedUserId;
    }


    public java.sql.Timestamp getFollowTime() {
      return followTime;
    }

    public void setFollowTime(java.sql.Timestamp followTime) {
      this.followTime = followTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Follow follow)) return false;

        if (!getFollowUserId().equals(follow.getFollowUserId())) return false;
        if (!getFollowedUserId().equals(follow.getFollowedUserId())) return false;
        return getFollowTime().equals(follow.getFollowTime());
    }

    @Override
    public int hashCode() {
        int result = getFollowUserId().hashCode();
        result = 31 * result + getFollowedUserId().hashCode();
        result = 31 * result + getFollowTime().hashCode();
        return result;
    }
}
