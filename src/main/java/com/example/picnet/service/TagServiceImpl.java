package com.example.picnet.service;

import com.example.picnet.mapping.DeletedTagRepository;
import com.example.picnet.mapping.TagPictureRepository;
import com.example.picnet.mapping.TagRepository;
import com.example.picnet.mapping.UserRepository;
import com.example.picnet.pojo.DeletedTag;
import com.example.picnet.pojo.Tag;
import com.example.picnet.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagServiceInter {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private DeletedTagRepository deletedTagRepository;
    @Autowired
    private TagPictureRepository tagPictureRepository;

    @Override
    public Boolean createTag(String tagName, Integer createUserId) {
        User user = userRepository.findById(createUserId).orElse(null);
        if (user == null) {
            return false;
        } else {
            if (!user.getUserStatus().equals("normal") || !user.getUserType().equals("admin")) {
                return false;
            } else {
                List<DeletedTag> deletedTagList = deletedTagRepository.findAll();
                if (deletedTagList.isEmpty()) {
                    if (tagRepository.findTagByTagName(tagName) != null) {
                        return false;
                    }
                    Tag tag = new Tag();
                    tag.setTagName(tagName);
                    tag.setTagStatus("normal");
                    tagRepository.save(tag);
                } else {
                    Integer foundId = deletedTagList.get(0).getTagId();
                    tagRepository.updateTagName(tagName, foundId);
                    tagRepository.updateTagStatus("normal", foundId);
                    deletedTagRepository.deleteByTagId(foundId);
                }
                return true;
            }
        }
    }

    @Override
    public Boolean deleteTag(String tagName, Integer deleteUserId) {
        User user = userRepository.findById(deleteUserId).orElse(null);
        if (user == null) {
            return false;
        } else {
            if (!user.getUserStatus().equals("normal") || !user.getUserType().equals("admin")) {
                return false;
            } else {
                Tag foundTag = tagRepository.findTagByTagName(tagName);
                if (foundTag == null) {
                    return false;
                }
                if (foundTag.getTagStatus().equals("deleted")) {
                    return false;
                } else {
                    tagRepository.updateTagStatus("deleted", foundTag.getTagId());
                    DeletedTag deletedTag = new DeletedTag();
                    deletedTag.setTagId(foundTag.getTagId());
                    deletedTagRepository.save(deletedTag);
                    return true;
                }
            }
        }
    }

    @Override
    public Boolean modifyTag(String originalTagName, String tagName, Integer modifyUserId) {
        User user = userRepository.findById(modifyUserId).orElse(null);
        if (user == null) {
            return false;
        } else {
            if (!user.getUserStatus().equals("normal") || !user.getUserType().equals("admin")) {
                return false;
            } else {
                Tag foundTag = tagRepository.findTagByTagName(originalTagName);
                if (foundTag == null) {
                    return false;
                }
                if (foundTag.getTagStatus().equals("deleted")) {
                    return false;
                } else {
                    tagRepository.updateTagName(tagName, foundTag.getTagId());
                    return true;
                }
            }
        }
    }

    @Override
    public Tag findTagByTagName(String tagName) {
        return tagRepository.findTagByTagName(tagName);
    }

    @Override
    public String findTagNameByTagId(Integer tagId) {
        return tagRepository.findTagNameByTagId(tagId);
    }

    @Override
    public List<Integer> findByTagId(Integer tagId) {
        return tagPictureRepository.findByTagId(tagId);
    }
}
