package com.example.picnet.service;

import com.example.picnet.pojo.CheckingPicture;

public interface CheckingPictureServiceInter {
    CheckingPicture getCheckingPictureByCheckingPictureId(Integer checkingPictureId);

    CheckingPicture findByCheckingPictureIdAndCheckingStatus(Integer checkingPictureId, String checkingStatus);
}
