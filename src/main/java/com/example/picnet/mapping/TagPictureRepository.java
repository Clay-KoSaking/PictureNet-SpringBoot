package com.example.picnet.mapping;

import com.example.picnet.pojo.TagPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TagPictureRepository extends JpaRepository<TagPicture, Integer> {
    @Transactional
    @Modifying
    @Query("delete from TagPicture tp where tp.pictureId = ?1")
    int deleteByPictureId(Integer pictureId);

    @Query(value = "select tag_id from tagpicture where picture_id = ?1", nativeQuery = true)
    List<Integer> findByPictureId(Integer pictureId);

    @Query(value = "select picture_id from tagpicture where tag_id = ?1", nativeQuery = true)
    List<Integer> findByTagId(Integer tagId);
}
