package com.example.picnet.mapping;

import com.example.picnet.pojo.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer> {
    @Query(value = "select * from likes where user_id = ?1 and picture_id = ?2", nativeQuery = true)
    Likes findLikesByUserIdAndPictureId(Integer userId, Integer pictureId);

    @Transactional
    @Modifying
    @Query("update Likes l set l.likeTime = ?1 where l.userId = ?2 and l.pictureId = ?3")
    int updateLikeTime(java.sql.Timestamp likeTime, Integer userId, Integer pictureId);

    @Transactional
    @Modifying
    @Query("update Likes l set l.isLiking = ?1 where l.userId = ?2 and l.pictureId = ?3")
    int updateIsLiking(Integer isLiking, Integer userId, Integer pictureId);

    @Query(value = "select count(1) from likes where picture_id = ?1", nativeQuery = true)
    Integer countByPictureId(Integer pictureId);
}
