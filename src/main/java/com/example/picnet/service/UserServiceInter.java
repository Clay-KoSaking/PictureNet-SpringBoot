package com.example.picnet.service;

import com.example.picnet.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.List;

public interface UserServiceInter {

    User getUser(Integer userId);

    User getUser(String userName);

    User getUser(String userName, String userPassword);

    Boolean createUser(String userName, String userPassword, String userEmail, MultipartFile userImage);

    Boolean deleteUser(Integer userId);

    Boolean banUser(Integer userId, String bannedReason);

    void updateUserName(String userName, Integer userId);

    void updateUserPassword(String userPassword, Integer userId);

    void updateUserEmail(String userEmail, Integer userId);

    void updateUserType(String userType, Integer userId);

    void updateUserStatus(String userStatus, Integer userId);

    void updateUserImage(byte[] userImage, Integer userId);

    void updateIsBanned(Integer isBanned, Integer originalIsBanned, Integer userId);

    void uploadUserImage(MultipartFile userImage, InputStream inputStream, BufferedImage bufferedImage);

    List<User> searchByKeyword(String keyword);

}
