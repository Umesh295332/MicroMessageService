package com.messageservice.demo.service;

import com.messageservice.demo.exception.ResourceMismatchException;
import com.messageservice.demo.exception.ResourceNotFoundException;
import com.messageservice.demo.model.Message;
import com.messageservice.demo.model.Profile;

import java.util.List;

public interface MessageServiceInt {

    public List<Message> getMessages(long profileId) ;

    public void addMessage(Message p) throws ResourceNotFoundException;

    public Message getMessage(long id) throws ResourceNotFoundException ;

    public void updateMessage(Message p, Long id) throws ResourceMismatchException, ResourceNotFoundException;

    public void deleteMessage (Long id) throws ResourceNotFoundException ;

}
