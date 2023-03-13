package com.example.picnet.mapping;

import com.example.picnet.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from user where user_name = ?1", nativeQuery = true)
    User findUserByUserName(String userName);

    @Query(value = "select * from user where user_name = ?1 and user_password = ?2", nativeQuery = true)
    User findUserByUserNameAndUserPassword(String userName, String userPassword);

    @Transactional
    @Modifying
    @Query("update User u set u.userName = ?1 where u.userId = ?2")
    int updateUserName(String userName, Integer userId);

    @Transactional
    @Modifying
    @Query("update User u set u.userPassword = ?1 where u.userId = ?2")
    int updateUserPassword(String userPassword, Integer userId);

    @Transactional
    @Modifying
    @Query("update User u set u.userEmail = ?1 where u.userId = ?2")
    int updateUserEmail(String userEmail, Integer userId);

    @Transactional
    @Modifying
    @Query("update User u set u.userType = ?1 where u.userId = ?2")
    int updateUserType(String userType, Integer userId);

    @Transactional
    @Modifying
    @Query("update User u set u.userStatus = ?1 where u.userId = ?2")
    int updateUserStatus(String userStatus, Integer userId);

    @Transactional
    @Modifying
    @Query("update User u set u.userImage = ?1 where u.userId = ?2")
    int updateUserImage(byte[] userImage, Integer userId);

    /* Used in SearchController. */
    @Query(value = "select * from user where match(user_name) against(?1) and user_status = 'normal'", nativeQuery = true)
    List<User> searchByKeyword(String keyword);


}

