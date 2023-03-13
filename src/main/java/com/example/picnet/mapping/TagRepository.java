package com.example.picnet.mapping;

import com.example.picnet.pojo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query(value = "select * from tag where tag_name = ?1", nativeQuery = true)
    Tag findTagByTagName(String tagName);

    @Transactional
    @Modifying
    @Query("delete from Tag t where t.tagName = ?1")
    int deleteByTagName(String tagName);

    @Transactional
    @Modifying
    @Query("update Tag t set t.tagName = ?1 where t.tagId = ?2")
    int updateTagName(String tagName, Integer tagId);

    @Transactional
    @Modifying
    @Query("update Tag t set t.tagStatus = ?1 where t.tagId = ?2")
    int updateTagStatus(String tagStatus, Integer tagId);

    @Query(value = "select tag_name from tag where tag_id = ?1", nativeQuery = true)
    String findTagNameByTagId(Integer tagId);

    /* Used in SearchController. */
    @Query("select t from Tag t where t.tagName like concat('%', :keyword, '%')")
    List<Tag> searchByKeyword(@Param("keyword") String keyword);
}
