package com.messageservice.demo.controller;

import com.messageservice.demo.exception.ResourceMismatchException;
import com.messageservice.demo.exception.ResourceNotFoundException;
import com.messageservice.demo.model.Profile;
import com.messageservice.demo.service.ProfileService;
import com.messageservice.demo.service.ProfileServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ProfileController {

    private final ProfileServiceInt profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @RequestMapping(method= RequestMethod.GET, value="/profiles")
    @ResponseStatus(value= HttpStatus.OK)
    public List<Profile> getProfiles(){
        return profileService.getProfiles();
    }

    @RequestMapping(method= RequestMethod.POST, value="/profiles")
    @ResponseStatus(value= HttpStatus.CREATED)
    public void addProfile(@RequestBody Profile profile) throws ResourceNotFoundException {
        profile.setCreated(LocalDateTime.now());
        profileService.addProfile(profile);
    }

    @RequestMapping("/profiles/{id}")
    @ResponseStatus(value= HttpStatus.OK)
    public Profile getProfile(@PathVariable Long id) throws ResourceNotFoundException {
        return profileService.getProfile(id);
    }

    @RequestMapping(method= RequestMethod.PUT, value="/profiles/{id}")
    @ResponseStatus(value= HttpStatus.OK)
    public void updateProfile(@RequestBody Profile profile, @PathVariable Long id) throws ResourceMismatchException, ResourceNotFoundException {
         profile.setCreated(LocalDateTime.now());
         profileService.updateProfile(profile, id);
    }

    @RequestMapping(method= RequestMethod.DELETE,value="/profiles/{id}")
    @ResponseStatus(value= HttpStatus.OK)
    public void deleteProfile(@PathVariable Long id) throws ResourceNotFoundException {
        profileService.deleteProfile(id);
    }
}
