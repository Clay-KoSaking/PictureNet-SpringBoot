package com.example.picnet.service;

import com.example.picnet.pojo.Comment;

public interface CommentServiceInter {
    Boolean createComment(Integer userId, Integer pictureId, String commentContent);

    Boolean deleteComment(Integer commentId, Integer userId);

    Comment getComment(Integer commentId);
}
