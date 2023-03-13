package com.example.picnet.service;

import com.example.picnet.mapping.MessageRepository;
import com.example.picnet.mapping.UserRepository;
import com.example.picnet.pojo.Message;
import com.example.picnet.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class MessageServiceImpl implements MessageServiceInter {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean sendMessage(Integer sendUserId, Integer receiveUserId, String messageContent) {
        User sendUser = userRepository.findById(sendUserId).orElse(null);
        User receiveUser = userRepository.findById(receiveUserId).orElse(null);
        if (sendUser == null || receiveUser == null) {
            return false;
        } else {
            Message message = new Message();
            Timestamp timestamp = new java.sql.Timestamp(new java.util.Date().getTime());
            message.setSendUserId(sendUserId);
            message.setReceiveUserId(receiveUserId);
            message.setSendTime(timestamp);
            message.setMessageContent(messageContent);
            messageRepository.save(message);
            return true;
        }
    }
}
