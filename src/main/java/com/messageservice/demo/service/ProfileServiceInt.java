package com.messageservice.demo.service;

import com.messageservice.demo.exception.ResourceMismatchException;
import com.messageservice.demo.exception.ResourceNotFoundException;
import com.messageservice.demo.model.Profile;

import java.util.List;

public interface ProfileServiceInt {

    List<Profile> getProfiles() ;

    void addProfile(Profile p) throws ResourceNotFoundException ;

    Profile getProfile(long id) throws ResourceNotFoundException ;

    void updateProfile(Profile p, Long id) throws ResourceMismatchException, ResourceNotFoundException;

    void deleteProfile(Long id) throws ResourceNotFoundException ;
}
