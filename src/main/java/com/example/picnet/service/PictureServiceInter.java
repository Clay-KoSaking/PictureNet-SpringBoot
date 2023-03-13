package com.example.picnet.service;

import com.example.picnet.pojo.Picture;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface PictureServiceInter {

    Boolean createPicture(Integer pictureAuthorId, String pictureName, MultipartFile pictureContent,
                          String pictureIntro, Set<String> tagNameSet);

    List<Integer> findPicturesByPictureAuthorId(Integer pictureAuthorId);

    Picture getPicture(Integer pictureId);

    void updatePictureViewPlusOne(Integer pictureId);

    void updatePictureTransmitPlusOne(Integer pictureId);

    Boolean transmitPicture(Integer pictureId, Integer sendUserId, Integer receiveUserId);

    Boolean deletePicture(Integer pictureId, Integer userId);

    Boolean modifyPicture(Integer pictureId, Integer userId, String pictureName,
                          byte[] pictureContent, String pictureIntro, Set<String> tagNameSet);

    List<Integer> findTagsByPictureId(Integer pictureId);

    List<Picture> searchByKeyword(String keyword);
}
