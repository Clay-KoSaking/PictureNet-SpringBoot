package com.example.picnet.mapping;

import com.example.picnet.pojo.DeletedComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DeletedCommentRepository extends JpaRepository<DeletedComment, Integer> {
    @Transactional
    @Modifying
    @Query("delete from DeletedComment dc where dc.commentId = ?1")
    int deleteByCommentId(Integer commentId);
}
