package com.messageservice.demo.repository;

import com.messageservice.demo.model.Comment;
import com.messageservice.demo.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByMessageId(long messageId);
}
