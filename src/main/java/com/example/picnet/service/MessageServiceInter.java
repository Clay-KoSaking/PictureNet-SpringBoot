package com.example.picnet.service;

public interface MessageServiceInter {

    Boolean sendMessage(Integer sendUserId, Integer receiveUserId, String messageContent);
}
