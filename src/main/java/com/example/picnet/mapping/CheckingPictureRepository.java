package com.example.picnet.mapping;

import com.example.picnet.pojo.CheckingPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CheckingPictureRepository extends JpaRepository<CheckingPicture, Integer> {

    @Transactional
    @Modifying
    @Query("update CheckingPicture cp set cp.checkingStatus = ?1 where cp.checkingPictureId = ?2")
    int updateCheckingStatus(String checkingStatus, Integer checkingPictureId);

    @Query(value = "select * from checkingpicture where checking_picture_id = ?1 and checking_status = ?2", nativeQuery = true)
    CheckingPicture findByCheckingPictureIdAndCheckingStatus(Integer checkingPictureId, String checkingStatus);
}
