package com.example.picnet.service;

import com.example.picnet.mapping.CommentRepository;
import com.example.picnet.mapping.DeletedCommentRepository;
import com.example.picnet.mapping.PictureRepository;
import com.example.picnet.mapping.UserRepository;
import com.example.picnet.pojo.Comment;
import com.example.picnet.pojo.DeletedComment;
import com.example.picnet.pojo.Picture;
import com.example.picnet.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentServiceInter {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private DeletedCommentRepository deletedCommentRepository;
    @Override
    public Boolean createComment(Integer userId, Integer pictureId, String commentContent) {
        User user = userRepository.findById(userId).orElse(null);
        Picture picture = pictureRepository.findById(pictureId).orElse(null);
        if (user == null || picture == null) {
            return false;
        }
        if (!user.getUserStatus().equals("normal")) {
            return false;
        }
        List<DeletedComment> deletedCommentList = deletedCommentRepository.findAll();
        Timestamp timestamp = new java.sql.Timestamp(new java.util.Date().getTime());
        if (deletedCommentList.isEmpty()) {
            Comment comment = new Comment();
            comment.setCommentUserId(userId);
            comment.setCommentPictureId(pictureId);
            comment.setCommentContent(commentContent);
            comment.setCommentTime(timestamp);
            comment.setCommentStatus("normal");
            commentRepository.save(comment);
        } else {
            Integer foundId = deletedCommentList.get(0).getCommentId();
            commentRepository.updateCommentUserId(userId, foundId);
            commentRepository.updateCommentPictureId(pictureId, foundId);
            commentRepository.updateCommentTime(timestamp, foundId);
            commentRepository.updateCommentContent(commentContent, foundId);
            commentRepository.updateCommentStatus("normal", foundId);
            deletedCommentRepository.deleteByCommentId(foundId);
        }
        return true;
    }

    @Override
    public Boolean deleteComment(Integer commentId, Integer userId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            return false;
        } else {
            User user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                return false;
            }
            if (!user.getUserStatus().equals("normal")) {
                return false;
            }
            if (!user.getUserType().equals("admin") && !comment.getCommentUserId().equals(userId)) {
                return false;
            } else {
                commentRepository.updateCommentStatus("deleted", commentId);
                DeletedComment deletedComment = new DeletedComment();
                deletedComment.setCommentId(commentId);
                deletedCommentRepository.save(deletedComment);
                return true;
            }
        }
    }

    @Override
    public Comment getComment(Integer commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }
}
