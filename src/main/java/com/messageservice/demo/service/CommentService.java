package com.messageservice.demo.service;

import com.messageservice.demo.exception.ResourceMismatchException;
import com.messageservice.demo.exception.ResourceNotFoundException;
import com.messageservice.demo.model.Comment;
import com.messageservice.demo.model.Message;
import com.messageservice.demo.repository.CommentRepository;
import com.messageservice.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements CommentServiceInt {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getComments(long messageId) {
        return new ArrayList<>(commentRepository.findByMessageId(messageId));
    }

    public void addComment(Comment c) throws ResourceNotFoundException {
        if (c == null)
            throw new ResourceNotFoundException("Please provide the valid comment resource");
        commentRepository.save(c);
    }

    public Comment getComment(long id) throws ResourceNotFoundException {
        if (id == 0)
            throw new ResourceNotFoundException("Please provide the valid comment Id");
        Optional<Comment> checkNull = commentRepository.findById(id);
        if (checkNull.isPresent())
            return checkNull.get();

        throw new ResourceNotFoundException("Comment Not found for the Id::" + id);

    }

    public void updateComment(Comment c, Long id) throws ResourceMismatchException, ResourceNotFoundException {
        if (id == 0)
            throw new ResourceNotFoundException("Please provide the valid comment Id");
        if (c.getId() == 0)
            c.setId(id);
        else if (id != c.getId())
            throw new ResourceMismatchException("Comment Id mismatch");
        commentRepository.save(c);
    }

    public void deleteComment(Long id) throws ResourceNotFoundException {
        if (id == 0)
            throw new ResourceNotFoundException("Please provide the valid comment Id");
        commentRepository.deleteById(id);
    }

    public boolean isCommentMatchesMessage(long commentId, long msgId) {
        List<Comment> commentLst = getComments(msgId);
        System.out.println("commentLst size"+commentLst.size());
        return commentLst.stream().anyMatch(comment -> comment.getId() == commentId);
    }

}
