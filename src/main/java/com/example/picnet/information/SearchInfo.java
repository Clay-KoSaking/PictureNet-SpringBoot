package com.example.picnet.information;

import java.util.Set;

public class SearchInfo {
    private Set<UserMicroInfo> userMicroInfoSet;
    private Set<PictureMicroInfo> pictureMicroInfoSet;

    public Set<UserMicroInfo> getUserMicroInfoSet() {
        return userMicroInfoSet;
    }

    public void setUserMicroInfoSet(Set<UserMicroInfo> userMicroInfoSet) {
        this.userMicroInfoSet = userMicroInfoSet;
    }

    public Set<PictureMicroInfo> getPictureMicroInfoSet() {
        return pictureMicroInfoSet;
    }

    public void setPictureMicroInfoSet(Set<PictureMicroInfo> pictureMicroInfoSet) {
        this.pictureMicroInfoSet = pictureMicroInfoSet;
    }

    public SearchInfo(Set<UserMicroInfo> userMicroInfoSet, Set<PictureMicroInfo> pictureMicroInfoSet) {
        this.userMicroInfoSet = userMicroInfoSet;
        this.pictureMicroInfoSet = pictureMicroInfoSet;
    }
}
