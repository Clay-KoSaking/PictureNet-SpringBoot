package com.example.picnet.mapping;

import com.example.picnet.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Transactional
    @Modifying
    @Query("update Comment c set c.commentStatus = ?1 where c.commentId = ?2")
    int updateCommentStatus(String commentStatus, Integer commentId);

    @Transactional
    @Modifying
    @Query("update Comment c set c.commentUserId = ?1 where c.commentId = ?2")
    int updateCommentUserId(Integer userId, Integer commentId);

    @Transactional
    @Modifying
    @Query("update Comment c set c.commentPictureId = ?1 where c.commentId = ?2")
    int updateCommentPictureId(Integer pictureId, Integer commentId);

    @Transactional
    @Modifying
    @Query("update Comment c set c.commentTime = ?1 where c.commentId = ?2")
    int updateCommentTime(java.sql.Timestamp timestamp, Integer commentId);

    @Transactional
    @Modifying
    @Query("update Comment c set c.commentContent = ?1 where c.commentId = ?2")
    int updateCommentContent(String commentContent, Integer commentId);
}
