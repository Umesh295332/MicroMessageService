package com.messageservice.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    private long id;
    private String comment;
    private LocalDateTime created;
    private long commentedByProfileId;

    @ManyToOne
    private Profile profile;

    @ManyToOne
    private Message message;

    public Comment() {
    }

    public Comment(long id, String comment, long commentedByProfileId, long profileId, long msgId) {
        this.id = id;
        this.comment = comment;
        this.created = LocalDateTime.now();
        this.profile = new Profile(commentedByProfileId,"","");
        this.message = new Message(msgId,"",profileId);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public long getCommentedByProfileId() {
        return commentedByProfileId;
    }

    public void setCommentedByProfileId(long commentedByProfileId) {
        this.commentedByProfileId = commentedByProfileId;
    }
}
