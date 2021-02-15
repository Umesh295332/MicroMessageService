package com.messageservice.demo.repository;

import com.messageservice.demo.model.Message;
import com.messageservice.demo.model.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    public List<Message> findByProfileId(long id);
}

