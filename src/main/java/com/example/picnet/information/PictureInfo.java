package com.example.picnet.information;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class PictureInfo {
    private String pictureAuthorName;
    private String pictureName;
    private byte[] pictureContent;
    private String pictureIntro;
    private Integer pictureView;
    private Integer pictureLikes;
    private Integer pictureTransmit;
    private java.sql.Timestamp pictureModifyTime;
    private Set<String> tagNameSet;
    private Set<Integer> recommendPictureIdSet;

    public String getPictureAuthorName() {
        return pictureAuthorName;
    }

    public void setPictureAuthorName(String pictureAuthorName) {
        this.pictureAuthorName = pictureAuthorName;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public byte[] getPictureContent() {
        return pictureContent;
    }

    public void setPictureContent(byte[] pictureContent) {
        this.pictureContent = pictureContent;
    }

    public String getPictureIntro() {
        return pictureIntro;
    }

    public void setPictureIntro(String pictureIntro) {
        this.pictureIntro = pictureIntro;
    }

    public Integer getPictureView() {
        return pictureView;
    }

    public void setPictureView(Integer pictureView) {
        this.pictureView = pictureView;
    }

    public Integer getPictureLikes() {
        return pictureLikes;
    }

    public void setPictureLikes(Integer pictureLikes) {
        this.pictureLikes = pictureLikes;
    }

    public Integer getPictureTransmit() {
        return pictureTransmit;
    }

    public void setPictureTransmit(Integer pictureTransmit) {
        this.pictureTransmit = pictureTransmit;
    }

    public Timestamp getPictureModifyTime() {
        return pictureModifyTime;
    }

    public void setPictureModifyTime(Timestamp pictureModifyTime) {
        this.pictureModifyTime = pictureModifyTime;
    }

    public Set<String> getTagNameSet() {
        return tagNameSet;
    }

    public void setTagNameSet(Set<String> tagNameSet) {
        this.tagNameSet = tagNameSet;
    }

    public Set<Integer> getRecommendPictureIdSet() {
        return recommendPictureIdSet;
    }

    public void setRecommendPictureIdSet(Set<Integer> recommendPictureIdSet) {
        this.recommendPictureIdSet = recommendPictureIdSet;
    }

    public PictureInfo(String pictureAuthorName, String pictureName, byte[] pictureContent,
                       String pictureIntro, Integer pictureView, Integer pictureLikes, Integer pictureTransmit,
                       Timestamp pictureModifyTime, Set<String> tagNameSet, Set<Integer> recommendPictureIdSet) {
        this.pictureAuthorName = pictureAuthorName;
        this.pictureName = pictureName;
        this.pictureContent = pictureContent;
        this.pictureIntro = pictureIntro;
        this.pictureView = pictureView;
        this.pictureLikes = pictureLikes;
        this.pictureTransmit = pictureTransmit;
        this.pictureModifyTime = pictureModifyTime;
        this.tagNameSet = tagNameSet;
        this.recommendPictureIdSet = recommendPictureIdSet;
    }
}
