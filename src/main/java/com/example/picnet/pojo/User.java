package com.example.picnet.pojo;

import jakarta.persistence.*;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Column(name = "user_name", nullable = false, length = 20)
    private String userName;
    @Column(name = "user_password", nullable = false, length = 32)
    private String userPassword;
    @Column(name = "user_email", nullable = false, length = 30)
    private String userEmail;
    @Column(name = "user_type", nullable = false)
    private String userType;
    @Column(name = "user_status", nullable = false)
    private String userStatus;
    @Column(name = "user_image", nullable = false)
    private byte[] userImage;

    private static final String salt = "neR>:Uum|-H+TPGN";


    public Integer getUserId() {
      return userId;
    }

    public void setUserId(Integer userId) {
      this.userId = userId;
    }


    public String getUserName() {
      return userName;
    }

    public void setUserName(String userName) {
      this.userName = userName;
    }


    public String getUserPassword() {
      return userPassword;
    }

    public void setUserPassword(String userPassword) {
      this.userPassword = userPassword;
    }


    public String getUserEmail() {
      return userEmail;
    }

    public void setUserEmail(String userEmail) {
      this.userEmail = userEmail;
    }


    public String getUserType() {
      return userType;
    }

    public void setUserType(String userType) {
      this.userType = userType;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public byte[] getUserImage() {
        return userImage;
    }

    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }

    public User(Integer userId, String userName, String userPassword, String userEmail, String userType) {
      this.userId = userId;
      this.userName = userName;
      this.userPassword = userPassword;
      this.userEmail = userEmail;
      this.userType = userType;
    }

    public User() {
    }

    public User(String userName, String userPassword, String userEmail) {
      this.userName = userName;
      this.userPassword = userPassword;
      this.userEmail = userEmail;
    }

    public static String getHashedPassword(String raw) {
        return DigestUtils.md5DigestAsHex((salt + raw + salt).getBytes(StandardCharsets.UTF_8));
    }

    public void setRawPassword(String raw) {
        this.userPassword = getHashedPassword(raw);
    }
}
