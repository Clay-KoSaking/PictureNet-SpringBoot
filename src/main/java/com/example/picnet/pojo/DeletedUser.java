package com.example.picnet.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "deleteduser")
public class DeletedUser {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;


    public Integer getUserId() {
      return userId;
    }

    public void setUserId(Integer userId) {
      this.userId = userId;
    }

}
