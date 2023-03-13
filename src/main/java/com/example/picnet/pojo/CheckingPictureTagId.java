package com.example.picnet.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CheckingPictureTagId implements Serializable {
    @Column(name = "checking_picture_id", nullable = false)
    private Integer checkingPictureId;
    @Column(name = "checking_picture_tag_id", nullable = false)
    private Integer checkingPictureTagId;

    public Integer getCheckingPictureId() {
        return checkingPictureId;
    }

    public void setCheckingPictureId(Integer checkingPictureId) {
        this.checkingPictureId = checkingPictureId;
    }

    public Integer getCheckingPictureTagId() {
        return checkingPictureTagId;
    }

    public void setCheckingPictureTagId(Integer checkingPictureTagId) {
        this.checkingPictureTagId = checkingPictureTagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckingPictureTagId that)) return false;

        if (!getCheckingPictureId().equals(that.getCheckingPictureId())) return false;
        return getCheckingPictureTagId().equals(that.getCheckingPictureTagId());
    }

    @Override
    public int hashCode() {
        int result = getCheckingPictureId().hashCode();
        result = 31 * result + getCheckingPictureTagId().hashCode();
        return result;
    }
}
