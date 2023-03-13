package com.example.picnet.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "deletedpicture")
public class DeletedPicture {
    @Id
    @Column(name = "picture_id", nullable = false)
    private Integer pictureId;


    public Integer getPictureId() {
      return pictureId;
    }

    public void setPictureId(Integer pictureId) {
    this.pictureId = pictureId;
  }

}
