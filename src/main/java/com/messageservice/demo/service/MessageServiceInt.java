package com.messageservice.demo.service;

import com.messageservice.demo.exception.ResourceMismatchException;
import com.messageservice.demo.exception.ResourceNotFoundException;
import com.messageservice.demo.model.Message;

import java.util.List;

public interface MessageServiceInt {

    List<Message> getMessages(long profileId) ;

    void addMessage(Message msg) throws ResourceNotFoundException;

    Message getMessage(long id) throws ResourceNotFoundException ;

    void updateMessage(Message msg, Long id) throws ResourceMismatchException, ResourceNotFoundException;

    void deleteMessage(Long id) throws ResourceNotFoundException ;

    boolean isMessageMatchesProfile(long msgId, long profileId);

}
