package com.example.picnet.mapping;

import com.example.picnet.pojo.BannedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BannedUserRepository extends JpaRepository<BannedUser, Integer> {

    @Transactional
    @Modifying
    @Query("update BannedUser bannedUser set bannedUser.isBanned = ?1 where bannedUser.isBanned = ?2 and bannedUser.userId = ?3")
    int updateIsBanned(Integer isBanned, Integer originalIsBanned, Integer userId);
}
