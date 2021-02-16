package com.messageservice.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    private long id;
    private String description;
    private LocalDateTime created;
    @ManyToOne
    private Profile profile;

    public Message() {
    }

    public Message(long id, String description, long profileId) {
        this.id = id;
        this.description = description;
        this.created = LocalDateTime.now();
        this.profile = new Profile(profileId,"","");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", profile=" + profile +
                '}';
    }
}
