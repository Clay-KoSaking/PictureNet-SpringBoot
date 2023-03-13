package com.example.picnet.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "result")
@IdClass(ResultId.class)
public class Result {
    @Id
    @Column(name = "picture_id", nullable = false)
    private Integer pictureId;
    @Id
    @Column(name = "checker_id", nullable = false)
    private Integer checkerId;
    @Id
    @Column(name = "check_time", nullable = false)
    private java.sql.Timestamp checkTime;
    @Column(name = "check_result", nullable = false)
    private String checkResult;


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


    public java.sql.Timestamp getCheckTime() {
      return checkTime;
    }

    public void setCheckTime(java.sql.Timestamp checkTime) {
      this.checkTime = checkTime;
    }


    public String getCheckResult() {
      return checkResult;
    }

    public void setCheckResult(String checkResult) {
    this.checkResult = checkResult;
  }
}
