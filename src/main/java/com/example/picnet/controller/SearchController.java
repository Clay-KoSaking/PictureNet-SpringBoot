package com.example.picnet.controller;

import com.example.picnet.information.PictureMicroInfo;
import com.example.picnet.information.SearchInfo;
import com.example.picnet.information.UserMicroInfo;
import com.example.picnet.pojo.Picture;
import com.example.picnet.pojo.User;
import com.example.picnet.response.ComplexResponse;
import com.example.picnet.service.PictureServiceImpl;
import com.example.picnet.service.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping(path = "/search")
public class SearchController {
    @Autowired
    private UserServiceInter userServiceInter;
    @Autowired
    private PictureServiceImpl pictureService;
    @GetMapping(path = "/{keyword}")
    public ComplexResponse search(@PathVariable("keyword") String keyword) {
        List<User> gotUserList = userServiceInter.searchByKeyword(keyword);
        List<Picture> gotPictureList = pictureService.searchByKeyword(keyword);

        Set<UserMicroInfo> userMicroInfoSet = new HashSet<>();
        Set<PictureMicroInfo> pictureMicroInfoSet = new HashSet<>();

        for (User user : gotUserList) {
            Integer userId = user.getUserId();
            String userName = user.getUserName();
            UserMicroInfo userMicroInfo = new UserMicroInfo();
            userMicroInfo.setUserId(userId);
            userMicroInfo.setUserName(userName);
            userMicroInfoSet.add(userMicroInfo);
        }
        for (Picture picture : gotPictureList) {
            Integer pictureId = picture.getPictureId();
            Integer pictureAuthorId = picture.getPictureAuthorId();
            String pictureName = picture.getPictureName();
            PictureMicroInfo pictureMicroInfo = new PictureMicroInfo();
            pictureMicroInfo.setPictureId(pictureId);
            pictureMicroInfo.setPictureAuthorId(pictureAuthorId);
            pictureMicroInfo.setPictureName(pictureName);
            pictureMicroInfoSet.add(pictureMicroInfo);
        }

        return new ComplexResponse(true, "Search successfully.", new SearchInfo(
                userMicroInfoSet, pictureMicroInfoSet
        ));
    }
}
