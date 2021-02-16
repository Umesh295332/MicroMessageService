package com.messageservice.demo.controller;

import com.messageservice.demo.exception.ResourceMismatchException;
import com.messageservice.demo.exception.ResourceNotFoundException;
import com.messageservice.demo.model.Comment;
import com.messageservice.demo.model.Message;
import com.messageservice.demo.model.Profile;
import com.messageservice.demo.service.CommentServiceInt;
import com.messageservice.demo.service.MessageServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class CommentController {

    private final CommentServiceInt commentService;
    private final MessageServiceInt messageService;

    @Autowired
    public CommentController(CommentServiceInt commentService,MessageServiceInt messageService) {
        this.commentService = commentService;
        this.messageService = messageService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "profiles/{profileId}/messages/{msgId}/comments")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Comment> getComments(@PathVariable long msgId) {
        return commentService.getComments(msgId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "profiles/{profileId}/messages/{msgId}/comments")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addComment(@RequestBody Comment comment, @PathVariable long profileId, @PathVariable long msgId) throws ResourceNotFoundException {
        comment.setCreated(LocalDateTime.now());
        comment.setMessage(new Message(msgId, "", profileId));
        comment.setProfile(new Profile(comment.getCommentedByProfileId(), "", ""));
        commentService.addComment(comment);
    }

    @RequestMapping("profiles/{profileId}/messages/{msgId}/comments/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Comment getComment(@PathVariable Long id, @PathVariable Long profileId,@PathVariable Long msgId) throws ResourceNotFoundException, ResourceMismatchException {
        if(! messageService.isMessageMatchesProfile(msgId, profileId))
            throw new ResourceMismatchException("Profile and Message mismatch");
        if(!commentService.isCommentMatchesMessage(id, msgId))
            throw new ResourceMismatchException("Comment and Message mismatch");
        return commentService.getComment(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "profiles/{profileId}/messages/{msgId}/comments/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateComment(@RequestBody Comment comment, @PathVariable Long id, @PathVariable Long profileId,@PathVariable Long msgId) throws ResourceMismatchException, ResourceNotFoundException {
        if(! messageService.isMessageMatchesProfile(msgId, profileId))
            throw new ResourceMismatchException("Profile and Message mismatch");
        if(!commentService.isCommentMatchesMessage(id, msgId))
            throw new ResourceMismatchException("Comment and Message mismatch");
        comment.setCreated(LocalDateTime.now());
        comment.setMessage(new Message(msgId, "", profileId));
        comment.setProfile(new Profile(comment.getCommentedByProfileId(), "", ""));
        commentService.updateComment(comment, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "profiles/{profileId}/messages/{msgId}/comments/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteComment(@PathVariable Long id, @PathVariable Long profileId,@PathVariable Long msgId) throws ResourceNotFoundException, ResourceMismatchException {
        if(! messageService.isMessageMatchesProfile(msgId, profileId))
            throw new ResourceMismatchException("Profile and Message mismatch");
        if(!commentService.isCommentMatchesMessage(id, msgId))
            throw new ResourceMismatchException("Comment and Message mismatch");
        commentService.deleteComment(id);
    }

}
