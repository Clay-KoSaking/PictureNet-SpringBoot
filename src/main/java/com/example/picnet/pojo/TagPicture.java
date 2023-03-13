package com.example.picnet.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "tagpicture")
@IdClass(TagPictureId.class)
public class TagPicture {
    @Id
    @Column(name = "tag_id", nullable = false)
    private Integer tagId;
    @Id
    @Column(name = "picture_id", nullable = false)
    private Integer pictureId;


    public Integer getTagId() {
      return tagId;
    }

    public void setTagId(Integer tagId) {
      this.tagId = tagId;
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
        if (!(o instanceof TagPicture that)) return false;

        if (!getTagId().equals(that.getTagId())) return false;
        return getPictureId().equals(that.getPictureId());
    }

    @Override
    public int hashCode() {
        int result = getTagId().hashCode();
        result = 31 * result + getPictureId().hashCode();
        return result;
    }
}
