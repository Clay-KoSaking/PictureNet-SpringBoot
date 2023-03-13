package com.example.picnet.pojo;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "picture")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id", nullable = false)
    private Integer pictureId;
    @Column(name = "picture_author_id", nullable = false)
    private Integer pictureAuthorId;
    @Column(name = "picture_name", nullable = false)
    private String pictureName;
    @Column(name = "picture_content", nullable = false)
    private byte[] pictureContent;
    @Column(name = "picture_intro", nullable = false)
    private String pictureIntro;
    @Column(name = "picture_view", nullable = false)
    private Integer pictureView;
    @Column(name = "picture_transmit", nullable = false)
    private Integer pictureTransmit;
    @Column(name = "picture_modify_time", nullable = false)
    private java.sql.Timestamp pictureModifyTime;
    @Column(name = "picture_status", nullable = false)
    private String pictureStatus;
    @Transient
    private Set<Tag> tagSet;


    public Integer getPictureId() {
      return pictureId;
    }

    public void setPictureId(Integer pictureId) {
      this.pictureId = pictureId;
    }


    public Integer getPictureAuthorId() {
      return pictureAuthorId;
    }

    public void setPictureAuthorId(Integer pictureAuthorId) {
      this.pictureAuthorId = pictureAuthorId;
    }


    public String getPictureName() {
      return pictureName;
    }

    public void setPictureName(String pictureName) {
      this.pictureName = pictureName;
    }


    public byte[] getPictureContent() {
      return pictureContent;
    }

    public void setPictureContent(byte[] pictureContent) {
      this.pictureContent = pictureContent;
    }


    public String getPictureIntro() {
      return pictureIntro;
    }

    public void setPictureIntro(String pictureIntro) {
      this.pictureIntro = pictureIntro;
    }

    public Integer getPictureView() {
      return pictureView;
    }

    public void setPictureView(Integer pictureView) {
      this.pictureView = pictureView;
    }

    public Integer getPictureTransmit() {
      return pictureTransmit;
    }

    public void setPictureTransmit(Integer pictureTransmit) {
      this.pictureTransmit = pictureTransmit;
    }

    public java.sql.Timestamp getPictureModifyTime() {
      return pictureModifyTime;
    }

    public void setPictureModifyTime(java.sql.Timestamp pictureModifyTime) {
      this.pictureModifyTime = pictureModifyTime;
    }

    public String getPictureStatus() {
        return pictureStatus;
    }

    public void setPictureStatus(String pictureStatus) {
        this.pictureStatus = pictureStatus;
    }

    public Set<Tag> getTagSet() {
        return tagSet;
    }

    public void setTagSet(Set<Tag> tagSet) {
        this.tagSet = tagSet;
    }
}
