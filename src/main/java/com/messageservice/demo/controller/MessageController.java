package com.messageservice.demo.controller;

import com.messageservice.demo.exception.ResourceMismatchException;
import com.messageservice.demo.exception.ResourceNotFoundException;
import com.messageservice.demo.model.Message;
import com.messageservice.demo.model.Profile;
import com.messageservice.demo.service.MessageServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class MessageController {

    private final MessageServiceInt messageService;

    @Autowired
    public MessageController(MessageServiceInt messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "profiles/{profileId}/messages")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Message> getMessages(@PathVariable long profileId) {
        return messageService.getMessages(profileId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "profiles/{profileId}/messages")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addMessage(@RequestBody Message message, @PathVariable long profileId) throws ResourceNotFoundException {
        message.setCreated(LocalDateTime.now());
        message.setProfile(new Profile(profileId, "", ""));
        messageService.addMessage(message);
    }

    @RequestMapping("profiles/{profileId}/messages/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Message getMessage(@PathVariable Long id) throws ResourceNotFoundException {
        return messageService.getMessage(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "profiles/{profileId}/messages/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateMessage(@RequestBody Message message, @PathVariable Long id, @PathVariable Long profileId) throws ResourceMismatchException, ResourceNotFoundException {
        message.setCreated(LocalDateTime.now());
        message.setProfile(new Profile(profileId, "", ""));
        messageService.updateMessage(message, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "profiles/{profileId}/messages/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteMessage(@PathVariable Long id) throws ResourceNotFoundException {
        messageService.deleteMessage(id);
    }

}
