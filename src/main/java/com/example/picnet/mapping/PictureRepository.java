package com.example.picnet.mapping;

import com.example.picnet.pojo.Picture;
import com.example.picnet.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {

    @Transactional
    @Modifying
    @Query("update Picture p set p.pictureAuthorId = ?1 where p.pictureId = ?2")
    int updatePictureAuthorId(Integer pictureAuthorId, Integer pictureId);

    @Transactional
    @Modifying
    @Query("update Picture p set p.pictureName = ?1 where p.pictureId = ?2")
    int updatePictureName(String pictureName, Integer pictureId);

    @Transactional
    @Modifying
    @Query("update Picture p set p.pictureContent = ?1 where p.pictureId = ?2")
    int updatePictureContent(byte[] pictureContent, Integer pictureId);

    @Transactional
    @Modifying
    @Query("update Picture p set p.pictureIntro = ?1 where p.pictureId = ?2")
    int updatePictureIntro(String pictureIntro, Integer pictureId);

    @Transactional
    @Modifying
    @Query("update Picture p set p.pictureStatus = ?1 where p.pictureId = ?2")
    int updatePictureStatus(String pictureStatus, Integer pictureId);

    @Transactional
    @Modifying
    @Query("update Picture p set p.pictureView = ?1 where p.pictureId = ?2")
    int updatePictureView(Integer pictureView, Integer pictureId);

    @Transactional
    @Modifying
    @Query("update Picture p set p.pictureTransmit = ?1 where p.pictureId = ?2")
    int updatePictureTransmit(Integer pictureTransmit, Integer pictureId);

    @Transactional
    @Modifying
    @Query("update Picture p set p.pictureModifyTime = ?1 where p.pictureId = ?2")
    int updatePictureModifyTime(java.sql.Timestamp pictureModifyTime, Integer pictureId);

    @Query(value = "select picture_id from picture where picture_author_id = ?1", nativeQuery = true)
    List<Integer> findPicturesByPictureAuthorId(Integer pictureAuthorId);

    @Transactional
    @Modifying
    @Query("update Picture p set p.pictureView = p.pictureView + 1 where p.pictureId = ?1")
    int updatePictureViewPlusOne(Integer pictureId);

    @Transactional
    @Modifying
    @Query("update Picture p set p.pictureTransmit = p.pictureTransmit + 1 where p.pictureId = ?1")
    int updatePictureTransmitPlusOne(Integer pictureId);

    /* Used in SearchController. */
    @Query(value = "select * from picture where match(picture_name) against(?1) and picture_status = 'normal'", nativeQuery = true)
    List<Picture> searchByKeyword(String keyword);

}
