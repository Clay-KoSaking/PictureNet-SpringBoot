package com.example.picnet.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Integer commentId;
    @Column(name = "comment_user_id", nullable = false)
    private Integer commentUserId;
    @Column(name = "comment_picture_id", nullable = false)
    private Integer commentPictureId;
    @Column(name = "comment_time", nullable = false)
    private java.sql.Timestamp commentTime;
    @Column(name = "comment_content", nullable = false)
    private String commentContent;
    @Column(name = "comment_status", nullable = false)
    private String commentStatus;


    public Integer getCommentId() {
      return commentId;
    }

    public void setCommentId(Integer commentId) {
      this.commentId = commentId;
    }


    public Integer getCommentUserId() {
      return commentUserId;
    }

    public void setCommentUserId(Integer commentUserId) {
      this.commentUserId = commentUserId;
    }


    public Integer getCommentPictureId() {
      return commentPictureId;
    }

    public void setCommentPictureId(Integer commentPictureId) {
      this.commentPictureId = commentPictureId;
    }


    public java.sql.Timestamp getCommentTime() {
      return commentTime;
    }

    public void setCommentTime(java.sql.Timestamp commentTime) {
      this.commentTime = commentTime;
    }


    public String getCommentContent() {
      return commentContent;
    }

    public void setCommentContent(String commentContent) {
    this.commentContent = commentContent;
  }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }
}
