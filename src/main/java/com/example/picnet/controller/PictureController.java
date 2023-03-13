package com.example.picnet.controller;

import com.example.picnet.information.PictureInfo;
import com.example.picnet.mapping.CheckingPictureRepository;
import com.example.picnet.mapping.TagRepository;
import com.example.picnet.pojo.*;
import com.example.picnet.response.ComplexResponse;
import com.example.picnet.service.*;
import com.vdurmont.emoji.EmojiParser;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.*;

import com.example.picnet.information.PictureInfo;

@RestController
@CrossOrigin
@RequestMapping(path = "/picture")
public class PictureController {
    @Autowired
    private UserServiceInter userServiceInter;
    @Autowired
    private PictureServiceInter pictureServiceInter;
    @Autowired
    private LikesServiceInter likesServiceInter;
    @Autowired
    private CheckingPictureServiceInter checkingPictureServiceInter;
    @Autowired
    private ResultServiceInter resultServiceInter;
    @Autowired
    private MessageServiceInter messageServiceInter;
    @Autowired
    private TagServiceInter tagServiceInter;

    @PostMapping(path = "/create")
    public ComplexResponse create(@RequestParam String pictureName, @RequestParam MultipartFile pictureContent,
                                  @RequestParam(required = false) String pictureIntro,
                                  @RequestParam(required = false) Set<String> tagNameSet,
                                  HttpSession httpSession) throws Exception {
        if (httpSession.getAttribute("user") == null) {
            return new ComplexResponse(false, "Non-logged-in users cannot create pictures.", null);
        } else {
            User user = userServiceInter.getUser((Integer) httpSession.getAttribute("user"));
            if (!user.getUserStatus().equals("normal")) {
                return new ComplexResponse(false, "You cannot create pictures now.", null);
            } else {
                InputStream inputStream = pictureContent.getInputStream();
                BufferedImage bufferedImage = ImageIO.read(inputStream);
                if (bufferedImage == null) {
                    return new ComplexResponse(false, "The file type uploaded is not a picture.", null);
                }
                if (pictureIntro != null) {
                    if (pictureServiceInter.createPicture(user.getUserId(), pictureName,
                            pictureContent, EmojiParser.parseToAliases(pictureIntro), tagNameSet)) {
                        return new ComplexResponse(true, "Create successfully, and the checkers are checking it now.", null);
                    } else {
                        return new ComplexResponse(false, "Server error.", null);
                    }
                } else {
                    if (pictureServiceInter.createPicture(user.getUserId(), pictureName,
                            pictureContent, "No profile.", tagNameSet)) {
                        return new ComplexResponse(true, "Create successfully, and the checkers are checking it now.", null);
                    } else {
                        return new ComplexResponse(false, "Server error.", null);
                    }
                }
            }
        }
    }

    @GetMapping(path = "/getInfo/{pictureId}")
    public ComplexResponse getInfo(@PathVariable("pictureId") Integer pictureId, HttpSession httpSession) {
        Picture foundPicture = pictureServiceInter.getPicture(pictureId);
        if (foundPicture == null) {
            return new ComplexResponse(false, "Cannot find the picture.", null);
        } else {
            if (foundPicture.getPictureStatus().equals("deleted")) {
                return new ComplexResponse(false, "Cannot find the picture.", null);
            }
            if (httpSession.getAttribute("user") != null) {
                pictureServiceInter.updatePictureViewPlusOne(pictureId);
                Integer view = foundPicture.getPictureView();
                foundPicture.setPictureView(view + 1);
            }
            List<Integer> tagList = pictureServiceInter.findTagsByPictureId(pictureId);
            Set<String> tagNameSet = new HashSet<>();
            Iterator it = tagList.iterator();
            while (it.hasNext()) {
                Integer tagId = (Integer) it.next();
                String tagName = tagServiceInter.findTagNameByTagId(tagId);
                tagNameSet.add(tagName);
            }

            Set<Integer> withSameTagPictureIdSet = new HashSet<>();
            for (Integer tagId : tagList) {
                List<Integer> pictureList = tagServiceInter.findByTagId(tagId);
                withSameTagPictureIdSet.addAll(pictureList);
            }
            List<Integer> withSameTagPictureIdList = new ArrayList<>(withSameTagPictureIdSet);
            Set<Integer> recommendPictureIdSet = new HashSet<>();
            Integer size = withSameTagPictureIdList.size();
            for (int i = 1; i <= Math.min(size, 10); i++) {
                int index = new Random().nextInt(size);
                Integer gotPictureId = withSameTagPictureIdList.get(index);
                if (!gotPictureId.equals(pictureId)) {
                    recommendPictureIdSet.add(gotPictureId);
                }
            }

            return new ComplexResponse(true, "", new PictureInfo(
                    userServiceInter.getUser(foundPicture.getPictureAuthorId()).getUserName(),
                    foundPicture.getPictureName(), foundPicture.getPictureContent(),
                    EmojiParser.parseToUnicode(foundPicture.getPictureIntro()),
                    foundPicture.getPictureView(), likesServiceInter.countByPictureId(pictureId),
                    foundPicture.getPictureTransmit(), foundPicture.getPictureModifyTime(),
                    tagNameSet, recommendPictureIdSet
            ));
        }
    }

    @PostMapping(path = "/like")
    public ComplexResponse like(@RequestParam Integer pictureId, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return new ComplexResponse(false, "Non-logged-in users cannot give it a like.", null);
        } else {
            User user = userServiceInter.getUser((Integer) httpSession.getAttribute("user"));
            if (!user.getUserStatus().equals("normal")) {
                return new ComplexResponse(false, "You cannot give it a like now.", null);
            }
            if (pictureServiceInter.getPicture(pictureId) == null) {
                return new ComplexResponse(false, "Cannot find the picture.", null);
            }
            if (likesServiceInter.createLikes(user.getUserId(), pictureId) == true) {
                return new ComplexResponse(true, "Give it a like successfully.", null);
            } else {
                return new ComplexResponse(false, "Server error.", null);
            }
        }
    }

    @PostMapping(path = "/dislike")
    public ComplexResponse dislike(@RequestParam Integer pictureId, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return new ComplexResponse(false, "Non-logged-in users cannot execute the operation.", null);
        } else {
            User user = userServiceInter.getUser((Integer) httpSession.getAttribute("user"));
            if (!user.getUserStatus().equals("normal")) {
                return new ComplexResponse(false, "You cannot execute the operation.", null);
            }
            if (pictureServiceInter.getPicture(pictureId) == null) {
                return new ComplexResponse(false, "Cannot find the picture", null);
            }
            if (likesServiceInter.cancelLikes(user.getUserId(), pictureId) == true) {
                return new ComplexResponse(true, "Execute successfully.", null);
            } else {
                return new ComplexResponse(false, "Server error.", null);
            }
        }
    }

    /* Modify pictures. */
    @PostMapping(path = "/modify")
    public ComplexResponse modify(@RequestParam Integer pictureId,
                                  @RequestParam(required = false) String pictureName,
                                  @RequestParam(required = false) MultipartFile pictureContent,
                                  @RequestParam(required = false) String pictureIntro,
                                  @RequestParam(required = false) Set<String> tagNameSet,
                                  HttpSession httpSession) throws Exception {
        if (httpSession.getAttribute("user") == null) {
            return new ComplexResponse(false, "Non-logged-in users cannot modify other's pictures.", null);
        } else {
            User user = userServiceInter.getUser((Integer) httpSession.getAttribute("user"));
            Picture picture = pictureServiceInter.getPicture(pictureId);
            if (picture == null) {
                return new ComplexResponse(false, "Cannot find the picture.", null);
            }
            if (!picture.getPictureAuthorId().equals(user.getUserId())) {
                return new ComplexResponse(false, "You cannot modify other's pictures.", null);
            }
            if (!user.getUserStatus().equals("normal")) {
                return new ComplexResponse(false, "You cannot modify pictures now.", null);
            }

            List<Integer> tagList = pictureServiceInter.findTagsByPictureId(pictureId);
            Set<String> newTagNameSet = new HashSet<>();
            Iterator it = tagList.iterator();
            while (it.hasNext()) {
                Integer tagId = (Integer) it.next();
                String tagName = tagServiceInter.findTagNameByTagId(tagId);
                newTagNameSet.add(tagName);
            }

            if (pictureName != null) {
                if (pictureServiceInter.modifyPicture(pictureId, user.getUserId(), pictureName,
                        picture.getPictureContent(), picture.getPictureIntro(), newTagNameSet) == true) {
                    return new ComplexResponse(true, "Modify successfully, and the checkers are checking it now.", null);
                } else {
                    return new ComplexResponse(false, "Server error.", null);
                }
            }
            if (pictureContent != null) {
                InputStream inputStream = pictureContent.getInputStream();
                BufferedImage bufferedImage = ImageIO.read(inputStream);
                if (bufferedImage == null) {
                    return new ComplexResponse(false, "The file type uploaded is not a picture.", null);
                }
                byte[] bytes = pictureContent.getBytes();
                if (pictureServiceInter.modifyPicture(pictureId, user.getUserId(), picture.getPictureName(),
                        bytes, picture.getPictureIntro(), newTagNameSet) == true) {
                    return new ComplexResponse(true, "Modify successfully, and the checkers are checking it now.", null);
                } else {
                    return new ComplexResponse(false, "Server error.", null);
                }
            }
            if (pictureIntro != null) {
                if (pictureServiceInter.modifyPicture(pictureId, user.getUserId(), picture.getPictureName(),
                        picture.getPictureContent(), EmojiParser.parseToAliases(pictureIntro), newTagNameSet) == true) {
                    return new ComplexResponse(true, "Modify successfully, and the checkers are checking it now.", null);
                } else {
                    return new ComplexResponse(false, "Server error.", null);
                }
            }
            /* Maybe: if (tagNameSet != null) {...} */
            if (!tagNameSet.isEmpty()) {
                if (pictureServiceInter.modifyPicture(pictureId, user.getUserId(), picture.getPictureName(),
                        picture.getPictureContent(), picture.getPictureIntro(), tagNameSet) == true) {
                    return new ComplexResponse(true, "Modify successfully, and the checkers are checking it now.", null);
                } else {
                    return new ComplexResponse(false, "Server error.", null);
                }
            } else {
                return new ComplexResponse(false, "Server error.", null);
            }
        }
    }

    @PostMapping(path = "/transmit")
    public ComplexResponse transmit(@RequestParam Integer pictureId, @RequestParam Integer receiveUserId, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return new ComplexResponse(false, "Non-logged-in users cannot transmit pictures to others.", null);
        } else {
            User user = userServiceInter.getUser((Integer) httpSession.getAttribute("user"));
            if (!user.getUserStatus().equals("normal")) {
                return new ComplexResponse(false, "You cannot transmit pictures to others now.", null);
            }
            if (pictureServiceInter.getPicture(pictureId) == null) {
                return new ComplexResponse(false, "Cannot find the picture.", null);
            }
            if (userServiceInter.getUser(receiveUserId) == null) {
                return new ComplexResponse(false, "Cannot find the receiver.", null);
            }
            if (pictureServiceInter.transmitPicture(pictureId, user.getUserId(), receiveUserId) == true) {
                return new ComplexResponse(true, "Transmit successfully.", null);
            } else {
                return new ComplexResponse(false, "Server error.", null);
            }
        }
    }

    @PostMapping(path = "/delete")
    public ComplexResponse delete(@RequestParam Integer pictureId, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return new ComplexResponse(false, "Non-logged-in users cannot delete pictures.", null);
        } else {
            User user = userServiceInter.getUser((Integer) httpSession.getAttribute("user"));
            if (!user.getUserStatus().equals("normal")) {
                return new ComplexResponse(false, "You cannot delete pictures now.", null);
            }
            if (pictureServiceInter.getPicture(pictureId) == null) {
                return new ComplexResponse(false, "Cannot find the picture.", null);
            }
            if (!pictureServiceInter.getPicture(pictureId).getPictureAuthorId().equals(user.getUserId()) &&
                    !(user.getUserType().equals("admin") && user.getUserStatus().equals("normal"))) {
                return new ComplexResponse(false, "You cannot delete pictures which don't belong to you.", null);
            }
            if (pictureServiceInter.deletePicture(pictureId, user.getUserId()) == true) {
                return new ComplexResponse(true, "Delete successfully.", null);
            } else {
                return new ComplexResponse(false, "Server error.", null);
            }
        }
    }

    @PostMapping(path = "/check")
    public ComplexResponse check(@RequestParam Integer checkingPictureId, @RequestParam String checkResult,
                                 HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return new ComplexResponse(false, "Non-logged-in users cannot check pictures.", null);
        } else {
            User user = userServiceInter.getUser((Integer) httpSession.getAttribute("user"));
            if (!user.getUserStatus().equals("normal")) {
                return new ComplexResponse(false, "You cannot check pictures now.", null);
            }
            if (!user.getUserType().equals("checker")) {
                return new ComplexResponse(false, "You are not a checker.", null);
            }
            if (checkingPictureServiceInter.getCheckingPictureByCheckingPictureId(checkingPictureId) == null) {
                return new ComplexResponse(false, "Cannot find the picture which waits for checking.", null);
            }
            if (!checkResult.equals("accessed") && !checkResult.equals("failed")) {
                return new ComplexResponse(false, "Wrong type.", null);
            }
            if (checkingPictureServiceInter.findByCheckingPictureIdAndCheckingStatus(checkingPictureId, "checked") != null) {
                return new ComplexResponse(false, "The picture has been already checked.", null);
            }
            CheckingPicture checkingPicture = checkingPictureServiceInter.getCheckingPictureByCheckingPictureId(checkingPictureId);
            if (checkResult.equals("accessed")) {
                if (resultServiceInter.check(checkingPictureId, user.getUserId(), checkResult) == true) {
                    messageServiceInter.sendMessage(1, checkingPicture.getPictureAuthorId(),
                            "Your picture " + "\"" + checkingPicture.getPictureName() + "\""
                                    + " has passed the check successfully.");
                    return new ComplexResponse(true, "Check successfully.", null);
                } else {
                    return new ComplexResponse(false, "Server error.", null);
                }
            } else {
                resultServiceInter.check(checkingPictureId, user.getUserId(), checkResult);
                messageServiceInter.sendMessage(1, checkingPicture.getPictureAuthorId(),
                        "Your picture " + "\"" + checkingPicture.getPictureName() + "\""
                                + " has not passed the check.");
                return new ComplexResponse(true, "Check successfully.", null);
            }
        }
    }
}
