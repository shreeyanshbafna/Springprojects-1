package com.start.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.start.Model.Profile;
import com.start.Model.User;
import com.start.Repository.ProfileRepository;
import com.start.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public User createUser(User user) {
    	userRepository.save(user);
    	 if (user.getName().equals("fail")) {
             throw new RuntimeException("Forced error for rollback test");
         }
        return user;
    }
    @Transactional
    @Override
    public List<User> createUsersOneByOne(List<User> users) {
        for (User user : users) {
            if (user.getProfile() != null) {
                user.getProfile().setUser(user); 
                // If profile has a reference back
            }
            userRepository.save(user); // Because of cascade, profile will be saved too
        }
        return users;
    }

    @Transactional
    public User createUserAndProfile(User user, Profile profile, boolean simulateError) {
    	User savedUser = userRepository.save(user);

        profile.setUser(savedUser); // Set the relation
        profileRepository.save(profile);

        if (simulateError) {
            throw new RuntimeException("Simulated error after saving profile");
        }
		return user;
}


    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }




}