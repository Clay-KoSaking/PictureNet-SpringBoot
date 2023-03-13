package com.example.picnet.service;

import com.example.picnet.mapping.*;
import com.example.picnet.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultServiceInter {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private CheckingPictureRepository checkingPictureRepository;
    @Autowired
    private CheckingPictureTagRepository checkingPictureTagRepository;
    @Autowired
    private TagPictureRepository tagPictureRepository;
    @Autowired
    private ResultRepository resultRepository;

    @Override
    public Boolean check(Integer checkingPictureId, Integer checkerId, String checkResult) {
        CheckingPicture checkingPicture = checkingPictureRepository.findById(checkingPictureId).orElse(null);
        User user = userRepository.findById(checkerId).orElse(null);
        Timestamp timestamp = new java.sql.Timestamp(new java.util.Date().getTime());
        if (checkingPicture == null || user == null) {
            return false;
        } else {
            if (!user.getUserType().equals("checker")) {
                return false;
            }
            if (!checkResult.equals("accessed") && !checkResult.equals("failed")) {
                return false;
            } else if (checkResult.equals("accessed")) {
                if (checkingPictureRepository.findByCheckingPictureIdAndCheckingStatus(checkingPictureId, "checked") != null) {
                    return false;
                }
                Result result = new Result();
                List<Integer> checkingPictureTagList =
                        checkingPictureTagRepository.findCheckingPictureTagsByCheckingPictureId(checkingPictureId);
                Iterator it = checkingPictureTagList.iterator();

                if (checkingPicture.getOriginalPictureId().equals(0)) {
                    Picture picture = new Picture();
                    picture.setPictureAuthorId(checkingPicture.getPictureAuthorId());
                    picture.setPictureName(checkingPicture.getPictureName());
                    picture.setPictureContent(checkingPicture.getPictureContent());
                    picture.setPictureIntro(checkingPicture.getPictureIntro());
                    picture.setPictureView(0);
                    picture.setPictureTransmit(0);
                    picture.setPictureModifyTime(timestamp);
                    picture.setPictureStatus("normal");
                    pictureRepository.save(picture);

                    Integer cnt = pictureRepository.findAll().size();
                    while (it.hasNext()) {
                        Integer tagId = (Integer) it.next();
                        TagPicture tagPicture = new TagPicture();
                        tagPicture.setTagId(tagId);
                        tagPicture.setPictureId(cnt);
                        tagPictureRepository.save(tagPicture);
                    }

                    result.setPictureId(cnt);
                } else {
                    Integer originalId = checkingPicture.getOriginalPictureId();
                    pictureRepository.updatePictureAuthorId(checkingPicture.getPictureAuthorId(), originalId);
                    pictureRepository.updatePictureName(checkingPicture.getPictureName(), originalId);
                    pictureRepository.updatePictureContent(checkingPicture.getPictureContent(), originalId);
                    pictureRepository.updatePictureIntro(checkingPicture.getPictureIntro(), originalId);

                    if (pictureRepository.findById(originalId).orElse(null).getPictureStatus().equals("deleted")) {
                        pictureRepository.updatePictureView(0, originalId);
                        pictureRepository.updatePictureTransmit(0, originalId);
                    }

                    pictureRepository.updatePictureModifyTime(timestamp, originalId);
                    pictureRepository.updatePictureStatus("normal", originalId);

                    while (it.hasNext()) {
                        Integer tagId = (Integer) it.next();
                        TagPicture tagPicture = new TagPicture();
                        tagPicture.setTagId(tagId);
                        tagPicture.setPictureId(originalId);
                        tagPictureRepository.save(tagPicture);
                    }

                    result.setPictureId(originalId);
                }
                checkingPictureTagRepository.deleteCheckingPictureTagByCheckingPictureId(checkingPictureId);
                checkingPictureRepository.updateCheckingStatus("checked", checkingPictureId);

                result.setCheckerId(checkerId);
                result.setCheckTime(timestamp);
                result.setCheckResult("accessed");
                resultRepository.save(result);

                return true;
            } else {
                if (checkingPicture.getOriginalPictureId().equals(0)) {
                    checkingPictureRepository.updateCheckingStatus("checked", checkingPictureId);
                    return false;
                } else {
                    Result result = new Result();
                    result.setPictureId(checkingPicture.getOriginalPictureId());
                    result.setCheckerId(checkerId);
                    result.setCheckTime(timestamp);
                    result.setCheckResult("failed");
                    resultRepository.save(result);

                    checkingPictureRepository.updateCheckingStatus("checked", checkingPictureId);
                    return true;
                }
            }
        }
    }

}
