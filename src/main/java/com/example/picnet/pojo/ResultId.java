package com.example.picnet.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
public class ResultId implements Serializable {
    @Column(name = "picture_id", nullable = false)
    private Integer pictureId;
    @Column(name = "checker_id", nullable = false)
    private Integer checkerId;
    @Column(name = "check_time", nullable = false)
    private java.sql.Timestamp checkTime;

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public Integer getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(Integer checkerId) {
        this.checkerId = checkerId;
    }

    public Timestamp getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Timestamp checkTime) {
        this.checkTime = checkTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultId resultId)) return false;

        if (!getPictureId().equals(resultId.getPictureId())) return false;
        if (!getCheckerId().equals(resultId.getCheckerId())) return false;
        return getCheckTime().equals(resultId.getCheckTime());
    }

    @Override
    public int hashCode() {
        int result = getPictureId().hashCode();
        result = 31 * result + getCheckerId().hashCode();
        result = 31 * result + getCheckTime().hashCode();
        return result;
    }
}
