package com.example.picnet.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class TagId implements Serializable {
    @Column(name = "tag_id", nullable = false)
    private Integer tagId;
    @Column(name = "tag_name", nullable = false)
    private String tagName;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagId tagId1)) return false;

        if (!getTagId().equals(tagId1.getTagId())) return false;
        return getTagName().equals(tagId1.getTagName());
    }

    @Override
    public int hashCode() {
        int result = getTagId().hashCode();
        result = 31 * result + getTagName().hashCode();
        return result;
    }
}
