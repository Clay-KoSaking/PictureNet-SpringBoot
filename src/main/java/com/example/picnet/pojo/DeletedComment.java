package com.example.picnet.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "deletedcomment")
public class DeletedComment {
    @Id
    @Column(name = "comment_id", nullable = false)
    private Integer commentId;


    public Integer getCommentId() {
      return commentId;
    }

    public void setCommentId(Integer commentId) {
    this.commentId = commentId;
  }

}
