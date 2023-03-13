package com.example.picnet.service;


import com.example.picnet.pojo.Follow;

public interface FollowServiceInter {

    Boolean follow(Integer followUserId, Integer followedUserId);

    Boolean unfollow(Integer followUserId, Integer followedUserId);

    Follow getFollowInfo(Integer followUserId, Integer followedUserId);
}
