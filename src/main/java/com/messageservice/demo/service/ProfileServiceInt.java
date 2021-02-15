package com.messageservice.demo.service;

import com.messageservice.demo.exception.ResourceMismatchException;
import com.messageservice.demo.exception.ResourceNotFoundException;
import com.messageservice.demo.model.Profile;

import java.util.List;

public interface ProfileServiceInt {

    public List<Profile> getProfiles() ;

    public void addProfile(Profile p) throws ResourceNotFoundException ;

    public Profile getProfile(long id) throws ResourceNotFoundException ;

    public void updateProfile(Profile p, Long id) throws ResourceMismatchException, ResourceNotFoundException;

    public void deleteProfile (Long id) throws ResourceNotFoundException ;
}
