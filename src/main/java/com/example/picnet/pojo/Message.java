package com.example.picnet.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "message")
@IdClass(MessageId.class)
public class Message {
    @Id
    @Column(name = "send_user_id", nullable = false)
    private Integer sendUserId;
    @Id
    @Column(name = "receive_user_id", nullable = false)
    private Integer receiveUserId;
    @Id
    @Column(name = "send_time", nullable = false)
    private java.sql.Timestamp sendTime;
    @Column(name = "message_content", nullable = false)
    private String messageContent;

    public Message() {
    }

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


    public java.sql.Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(java.sql.Timestamp sendTime) {
        this.sendTime = sendTime;
    }


    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

}
