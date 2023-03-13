package com.example.picnet.service;

import com.example.picnet.mapping.*;
import com.example.picnet.pojo.*;
import com.example.picnet.response.ComplexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class PictureServiceImpl implements PictureServiceInter {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private DeletedPictureRepository deletedPictureRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TagPictureRepository tagPictureRepository;
    @Autowired
    private CheckingPictureRepository checkingPictureRepository;
    @Autowired
    private CheckingPictureTagRepository checkingPictureTagRepository;
    @Autowired
    private ResultRepository resultRepository;

    @Override
    public Picture getPicture(Integer pictureId) {
        return pictureRepository.findById(pictureId).orElse(null);
    }

    @Override
    public Boolean createPicture(Integer pictureAuthorId, String pictureName,
                                 MultipartFile pictureContent, String pictureIntro, Set<String> tagNameSet) {
        try {
            /* Require: pictureAuthorId, pictureName and pictureContent are not null. */
            if (pictureName == null || pictureContent == null) {
                return false;
            }
            /* Determine if the uploaded file is an image. */
            InputStream inputStream = pictureContent.getInputStream();
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            if (bufferedImage == null) {
                return false;
            }
            /* Non-existent users and banned users cannot create images. */
            User user = userRepository.findById(pictureAuthorId).orElse(null);
            if (user == null) {
                return false;
            } else {
                if (!user.getUserStatus().equals("normal")) {
                    return false;
                } else {
                    List<DeletedPicture> deletedPictureList = deletedPictureRepository.findAll();
                    byte[] bytes = pictureContent.getBytes();
//                    Timestamp timestamp = new java.sql.Timestamp(new java.util.Date().getTime());
                    Iterator it = tagNameSet.iterator();
                    if (!tagNameSet.isEmpty()) {
                        for (String tagName : tagNameSet) {
                            Tag foundTag = tagRepository.findTagByTagName(tagName);
                            if (foundTag == null) {
                                return false;
                            }
                        }
                    }
                    CheckingPicture checkingPicture = new CheckingPicture();
                    checkingPicture.setPictureAuthorId(pictureAuthorId);
                    checkingPicture.setPictureName(pictureName);
                    checkingPicture.setPictureContent(bytes);
                    checkingPicture.setPictureIntro(pictureIntro);
                    checkingPicture.setCheckingStatus("checking");
                    if (deletedPictureList.isEmpty()) {
                        checkingPicture.setOriginalPictureId(0);

//                        Picture picture = new Picture();
//                        picture.setPictureAuthorId(pictureAuthorId);
//                        picture.setPictureName(pictureName);
//                        picture.setPictureContent(bytes);
//                        picture.setPictureIntro(pictureIntro);
//                        picture.setPictureView(0);
//                        picture.setPictureTransmit(0);
//                        picture.setPictureModifyTime(timestamp);
//                        picture.setPictureStatus("normal");
//                        pictureRepository.save(picture);

//                        Integer cnt = pictureRepository.findAll().size();
//                        while (it.hasNext()) {
//                            String tagName = (String) it.next();
//                            Tag foundTag = tagRepository.findTagByTagName(tagName);
//                            TagPicture tagPicture = new TagPicture();
//                            tagPicture.setTagId(foundTag.getTagId());
//                            tagPicture.setPictureId(cnt);
//                            tagPictureRepository.save(tagPicture);
//                        }
                    } else {
                        Integer foundId = deletedPictureList.get(0).getPictureId();
                        checkingPicture.setOriginalPictureId(foundId);
                        deletedPictureRepository.deleteByPictureId(foundId);

//                        if (!tagNameSet.isEmpty()) {
//                            for (String tagName : tagNameSet) {
//                                Tag foundTag = tagRepository.findTagByTagName(tagName);
//                                if (foundTag == null) {
//                                    return false;
//                                }
//                            }
//                            while (it.hasNext()) {
//                                String tagName = (String) it.next();
//                                Tag foundTag = tagRepository.findTagByTagName(tagName);
//                                TagPicture tagPicture = new TagPicture();
//                                tagPicture.setTagId(foundTag.getTagId());
//                                tagPicture.setPictureId(foundId);
//                                tagPictureRepository.save(tagPicture);
//                            }
//                        }
//                        pictureRepository.updatePictureAuthorId(pictureAuthorId, foundId);
//                        pictureRepository.updatePictureName(pictureName, foundId);
//                        pictureRepository.updatePictureContent(bytes, foundId);
//                        pictureRepository.updatePictureIntro(pictureIntro, foundId);
//                        pictureRepository.updatePictureView(0, foundId);
//                        pictureRepository.updatePictureTransmit(0, foundId);
//                        pictureRepository.updatePictureModifyTime(timestamp, foundId);
//                        pictureRepository.updatePictureStatus("normal",foundId);
//                        deletedPictureRepository.deleteByPictureId(foundId);
                    }
                    checkingPictureRepository.save(checkingPicture);

                    Integer cnt = checkingPictureRepository.findAll().size();
                    while (it.hasNext()) {
                        String tagName = (String) it.next();
                        Tag foundTag = tagRepository.findTagByTagName(tagName);
                        CheckingPictureTag checkingPictureTag = new CheckingPictureTag();
                        checkingPictureTag.setCheckingPictureId(cnt);
                        checkingPictureTag.setCheckingPictureTagId(foundTag.getTagId());
                        checkingPictureTagRepository.save(checkingPictureTag);
                    }
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Integer> findPicturesByPictureAuthorId(Integer pictureAuthorId) {
        return pictureRepository.findPicturesByPictureAuthorId(pictureAuthorId);
    }

    @Override
    public void updatePictureViewPlusOne(Integer pictureId) {
        pictureRepository.updatePictureViewPlusOne(pictureId);
    }

    @Override
    public void updatePictureTransmitPlusOne(Integer pictureId) {
        pictureRepository.updatePictureTransmitPlusOne(pictureId);
    }

    @Override
    public Boolean transmitPicture(Integer pictureId, Integer sendUserId, Integer receiveUserId) {
        Picture picture = pictureRepository.findById(pictureId).orElse(null);
        User sendUser = userRepository.findById(sendUserId).orElse(null);
        User receiveUser = userRepository.findById(receiveUserId).orElse(null);
        if (picture == null || sendUser == null || receiveUser == null) {
            return false;
        } else {
            if (!sendUser.getUserStatus().equals("normal")) {
                return false;
            }
            Message message = new Message();
            Timestamp timestamp = new java.sql.Timestamp(new java.util.Date().getTime());
            String content = "I really love this picture! Let's have a look ~~~ pictureId: " + pictureId;
            message.setSendUserId(sendUserId);
            message.setReceiveUserId(receiveUserId);
            message.setSendTime(timestamp);
            message.setMessageContent(content);
            messageRepository.save(message);
            pictureRepository.updatePictureTransmitPlusOne(pictureId);
            return true;
        }
    }

    @Override
    public Boolean deletePicture(Integer pictureId, Integer userId) {
        Picture picture = pictureRepository.findById(pictureId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (picture == null || user == null) {
            return false;
        }
        if (picture.getPictureStatus().equals("deleted")) {
            return false;
        }
        if (!picture.getPictureAuthorId().equals(userId) &&
                !(user.getUserType().equals("admin") && user.getUserStatus().equals("normal"))) {
            return false;
        } else {
            pictureRepository.updatePictureStatus("deleted", pictureId);
            DeletedPicture deletedPicture = new DeletedPicture();
            deletedPicture.setPictureId(pictureId);
            deletedPictureRepository.save(deletedPicture);
            tagPictureRepository.deleteByPictureId(pictureId);
            resultRepository.deleteByPictureId(pictureId);
            return true;
        }
    }

    @Override
    public Boolean modifyPicture(Integer pictureId, Integer userId, String pictureName,
                                 byte[] pictureContent, String pictureIntro, Set<String> tagNameSet) {
        try {
            Picture picture = pictureRepository.findById(pictureId).orElse(null);
            User user = userRepository.findById(userId).orElse(null);
            if (picture == null || user == null) {
                return false;
            }
            if (!picture.getPictureAuthorId().equals(user.getUserId())) {
                return false;
            }

            Iterator it = tagNameSet.iterator();
            if (!tagNameSet.isEmpty()) {
                for (String tagName : tagNameSet) {
                    Tag foundTag = tagRepository.findTagByTagName(tagName);
                    if (foundTag == null) {
                        return false;
                    }
                }
            }

            CheckingPicture checkingPicture = new CheckingPicture();
            checkingPicture.setPictureAuthorId(userId);
            checkingPicture.setPictureName(pictureName);
            checkingPicture.setPictureContent(pictureContent);
            checkingPicture.setPictureIntro(pictureIntro);
            checkingPicture.setOriginalPictureId(pictureId);
            checkingPicture.setCheckingStatus("checking");
            checkingPictureRepository.save(checkingPicture);

            tagPictureRepository.deleteByPictureId(pictureId);

            Integer cnt = checkingPictureRepository.findAll().size();
            while (it.hasNext()) {
                String tagName = (String) it.next();
                Tag foundTag = tagRepository.findTagByTagName(tagName);
                CheckingPictureTag checkingPictureTag = new CheckingPictureTag();
                checkingPictureTag.setCheckingPictureId(cnt);
                checkingPictureTag.setCheckingPictureTagId(foundTag.getTagId());
                checkingPictureTagRepository.save(checkingPictureTag);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Integer> findTagsByPictureId(Integer pictureId) {
        return tagPictureRepository.findByPictureId(pictureId);
    }

    @Override
    public List<Picture> searchByKeyword(String keyword) {
        return pictureRepository.searchByKeyword(keyword);
    }
}
