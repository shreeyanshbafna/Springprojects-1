package com.start.Service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.start.Model.Profile;
import com.start.Model.User;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    User createUserAndProfile(User user, Profile profile, boolean fail);
	List<User> createUsersOneByOne(List<User> users);
	Page<User> getAllUsers(Pageable pageable);

}
