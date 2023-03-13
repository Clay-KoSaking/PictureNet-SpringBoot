package com.example.picnet.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "likes")
@IdClass(LikesId.class)
public class Likes {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Id
    @Column(name = "picture_id", nullable = false)
    private Integer pictureId;
    @Column(name = "like_time", nullable = false)
    private java.sql.Timestamp likeTime;
    @Column(name = "is_liking", nullable = false)
    private Integer isLiking;


    public Integer getUserId() {
      return userId;
    }

    public void setUserId(Integer userId) {
      this.userId = userId;
    }


    public Integer getPictureId() {
      return pictureId;
    }

    public void setPictureId(Integer pictureId) {
      this.pictureId = pictureId;
    }


    public java.sql.Timestamp getLikeTime() {
      return likeTime;
    }

    public void setLikeTime(java.sql.Timestamp likeTime) {
      this.likeTime = likeTime;
    }

    public Integer getIsLiking() {
        return isLiking;
    }

    public void setIsLiking(Integer isLiking) {
        this.isLiking = isLiking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Likes likes)) return false;

        if (!getUserId().equals(likes.getUserId())) return false;
        if (!getPictureId().equals(likes.getPictureId())) return false;
        return getLikeTime().equals(likes.getLikeTime());
    }

    @Override
    public int hashCode() {
        int result = getUserId().hashCode();
        result = 31 * result + getPictureId().hashCode();
        result = 31 * result + getLikeTime().hashCode();
        return result;
    }
}
