package com.example.picnet.mapping;

import com.example.picnet.pojo.CheckingPictureTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CheckingPictureTagRepository extends JpaRepository<CheckingPictureTag, Integer> {
    @Query(value = "select checking_picture_tag_id from checkingpicturetag where checking_picture_id = ?1", nativeQuery = true)
    List<Integer> findCheckingPictureTagsByCheckingPictureId(Integer checkingPictureId);

    @Transactional
    @Modifying
    @Query("delete from CheckingPictureTag cpt where cpt.checkingPictureId = ?1")
    int deleteCheckingPictureTagByCheckingPictureId(Integer checkingPictureId);
}
