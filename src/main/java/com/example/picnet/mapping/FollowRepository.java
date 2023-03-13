package com.example.picnet.mapping;

import com.example.picnet.pojo.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {

    @Transactional
    @Modifying
    @Query("delete from Follow follow where follow.followUserId = ?1 and follow.followedUserId = ?2")
    int deleteByFollowUserIdAndFollowedUserId(Integer followUserId, Integer followedUserId);

    @Query(value = "select * from follow where follow_user_id = ?1 and followed_user_id = ?2", nativeQuery = true)
    Follow findFollowByFollowUserIdAndFollowedUserId(Integer followUserId, Integer followedUserId);

}
