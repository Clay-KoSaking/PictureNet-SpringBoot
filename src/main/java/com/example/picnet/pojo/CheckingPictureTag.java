package com.example.picnet.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "checkingpicturetag")
@IdClass(CheckingPictureTagId.class)
public class CheckingPictureTag {

  @Id
  @Column(name = "checking_picture_id", nullable = false)
  private Integer checkingPictureId;
  @Id
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

}
