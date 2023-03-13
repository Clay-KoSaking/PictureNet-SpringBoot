package com.example.picnet.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "tag")
@IdClass(TagId.class)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id", nullable = false)
    private Integer tagId;
    @Id
    @Column(name = "tag_name", nullable = false)
    private String tagName;
    @Column(name = "tag_status", nullable = false)
    private String tagStatus;


    public Integer getTagId() {
      return tagId;
    }

    public void setTagId(Integer tagId) {
      this.tagId = tagId;
    }


    public String getTagName() {
      return tagName;
    }

    public void setTagName(String tagName) {
      this.tagName = tagName;
    }

    public String getTagStatus() {
        return tagStatus;
    }

    public void setTagStatus(String tagStatus) {
        this.tagStatus = tagStatus;
    }
}
