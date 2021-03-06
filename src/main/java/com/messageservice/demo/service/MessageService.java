package com.messageservice.demo.service;

import com.messageservice.demo.exception.ResourceMismatchException;
import com.messageservice.demo.exception.ResourceNotFoundException;
import com.messageservice.demo.model.Message;
import com.messageservice.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements MessageServiceInt {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages(long profileId) {
        return new ArrayList<>(messageRepository.findByProfileId(profileId));
    }

    public void addMessage(Message msg) throws ResourceNotFoundException {
        if (msg == null)
            throw new ResourceNotFoundException("Please provide the valid message resource");
        messageRepository.save(msg);
    }

    public Message getMessage(long id) throws ResourceNotFoundException {
        if (id == 0)
            throw new ResourceNotFoundException("Please provide the valid message Id");
        Optional<Message> checkNull = messageRepository.findById(id);
        if (checkNull.isPresent())
            return checkNull.get();

        throw new ResourceNotFoundException("Message Not found for the Id::" + id);

    }

    public void updateMessage(Message msg, Long id) throws ResourceMismatchException, ResourceNotFoundException {
        if (id == 0)
            throw new ResourceNotFoundException("Please provide the valid message Id");
        if (msg.getId() == 0)
            msg.setId(id);
        else if (id != msg.getId())
            throw new ResourceMismatchException("Message Id mismatch");
        messageRepository.save(msg);
    }

    public void deleteMessage(Long id) throws ResourceNotFoundException {
        if (id == 0)
            throw new ResourceNotFoundException("Please provide the valid message Id");
        messageRepository.deleteById(id);
    }

    public boolean isMessageMatchesProfile(long msgId, long profileId) {
       List<Message> msgLst = getMessages(profileId);
       System.out.println("msgLst size"+msgLst.size());
        return msgLst.stream().anyMatch(msg -> msg.getId() == msgId);
    }

}
