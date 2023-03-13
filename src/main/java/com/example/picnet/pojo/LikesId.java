package com.example.picnet.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
public class LikesId implements Serializable {
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Column(name = "picture_id", nullable = false)
    private Integer pictureId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LikesId likesId)) return false;

        if (!getUserId().equals(likesId.getUserId())) return false;
        return getPictureId().equals(likesId.getPictureId());
    }

    @Override
    public int hashCode() {
        int result = getUserId().hashCode();
        result = 31 * result + getPictureId().hashCode();
        return result;
    }
}
