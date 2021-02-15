package com.messageservice.demo.service;

import com.messageservice.demo.exception.ResourceMismatchException;
import com.messageservice.demo.exception.ResourceNotFoundException;
import com.messageservice.demo.model.Profile;
import com.messageservice.demo.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService implements ProfileServiceInt {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<Profile> getProfiles() {
        List<Profile> profiles = new ArrayList<>();
        profileRepository.findAll().forEach(profiles::add);
        return profiles;
    }

    public void addProfile(Profile p) throws ResourceNotFoundException {
        if (p == null)
            throw new ResourceNotFoundException("Please provide the valid profile resource");
        profileRepository.save(p);
    }

    public Profile getProfile(long id) throws ResourceNotFoundException {
        if (id == 0)
            throw new ResourceNotFoundException("Please provide the valid profile Id");
        Optional<Profile> checkNull = profileRepository.findById(id);
        if (checkNull.isPresent())
            return checkNull.get();

        throw new ResourceNotFoundException("Profile Not found for the Id::" + id);

    }

    public void updateProfile(Profile p, Long id) throws ResourceMismatchException, ResourceNotFoundException {
        if (id == 0)
                throw new ResourceNotFoundException("Please provide the valid profile Id");
        if (p.getId() == 0)
            p.setId(id);
        else if (id != p.getId())
            throw new ResourceMismatchException("Profile Id mismatch");
        profileRepository.save(p);
        }

        public void deleteProfile (Long id) throws ResourceNotFoundException {
            if (id == 0)
                throw new ResourceNotFoundException("Please provide the valid profile Id");
            profileRepository.deleteById(id);
        }
    }
