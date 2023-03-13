package com.example.picnet.service;

public interface LikesServiceInter {
    Boolean createLikes(Integer userId, Integer pictureId);

    Boolean cancelLikes(Integer userId, Integer pictureId);

    Integer countByPictureId(Integer pictureId);
}
