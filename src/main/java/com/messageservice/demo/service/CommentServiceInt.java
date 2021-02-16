package com.messageservice.demo.service;

import com.messageservice.demo.exception.ResourceMismatchException;
import com.messageservice.demo.exception.ResourceNotFoundException;
import com.messageservice.demo.model.Comment;

import java.util.List;

public interface CommentServiceInt {

    List<Comment> getComments(long msgId) ;

    void addComment(Comment c) throws ResourceNotFoundException;

    Comment getComment(long id) throws ResourceNotFoundException ;

    void updateComment(Comment c, Long id) throws ResourceMismatchException, ResourceNotFoundException;

    void deleteComment(Long id) throws ResourceNotFoundException ;

    boolean isCommentMatchesMessage(long Id, long messageId);

}
