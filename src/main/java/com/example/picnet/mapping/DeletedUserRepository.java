package com.example.picnet.mapping;

import com.example.picnet.pojo.DeletedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DeletedUserRepository extends JpaRepository<DeletedUser, Integer> {

    @Transactional
    @Modifying
    @Query("delete from DeletedUser deletedUser where deletedUser.userId = ?1")
    int deleteByUserId(Integer userId);
}
