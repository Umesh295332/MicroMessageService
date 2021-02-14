package com.messageservice.demo.profile.controller;

import com.messageservice.demo.profile.model.Profile;
import com.messageservice.demo.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @RequestMapping("")
    public String processProfile(){
        return "Profile Controller";
    }
    @RequestMapping("/profiles")
    public List<Profile> getProfiles(){
        return profileService.getProfiles();
    }
    @RequestMapping(method= RequestMethod.POST, value="/profiles")
    public void addProfile(@RequestBody Profile profile){
        profileService.addProfile(profile);
    }
    @RequestMapping("/profiles/{id}")
    public Profile getProfile(@PathVariable Long id){
        return profileService.getProfile(id);
    }

    @RequestMapping(method= RequestMethod.PUT, value="/profiles/{id}")
    public void updateProfile(@RequestBody Profile profile, @PathVariable Long id){
         profileService.updateProfile(profile, id);
    }

    @RequestMapping(method= RequestMethod.DELETE,value="/profiles/{id}")
    public void deleteProfile(@PathVariable Long id){
        profileService.deleteProfile(id);
    }
}
