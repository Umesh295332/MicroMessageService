package com.messageservice.demo.profile.service;

import com.messageservice.demo.profile.model.Profile;
import com.messageservice.demo.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> getProfiles(){
        List<Profile> profiles = new ArrayList<>();
        profileRepository.findAll().forEach(profiles::add);
        return profiles;
    }

    public void addProfile(Profile p){
        profileRepository.save(p);
    }

    public Profile getProfile(long id){
         Optional<Profile> checkNull= profileRepository.findById(id);

         if(checkNull.isPresent()){
             return checkNull.get();
         }
         return   null;

    }
    public void updateProfile(Profile p,Long id){
        profileRepository.save(p);
    }
    public void deleteProfile(Long id){
        profileRepository.deleteById(id);
    }
}
