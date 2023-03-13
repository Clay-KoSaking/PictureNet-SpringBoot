package com.example.picnet.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
public class MessageId implements Serializable {
    @Column(name = "send_user_id", nullable = false)
    private Integer sendUserId;
    @Column(name = "receive_user_id", nullable = false)
    private Integer receiveUserId;
    @Column(name = "send_time", nullable = false)
    private java.sql.Timestamp sendTime;

    public Integer getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(Integer sendUserId) {
        this.sendUserId = sendUserId;
    }

    public Integer getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(Integer receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageId messageId)) return false;

        if (!getSendUserId().equals(messageId.getSendUserId())) return false;
        if (!getReceiveUserId().equals(messageId.getReceiveUserId())) return false;
        return getSendTime().equals(messageId.getSendTime());
    }

    @Override
    public int hashCode() {
        int result = getSendUserId().hashCode();
        result = 31 * result + getReceiveUserId().hashCode();
        result = 31 * result + getSendTime().hashCode();
        return result;
    }
}
