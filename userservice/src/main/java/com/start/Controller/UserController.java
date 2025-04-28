package com.start.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

import com.start.Model.Profile;
import com.start.Model.User;
import com.start.Model.UserProfileDTO;
import com.start.Service.UserService;


	@RestController
	@RequestMapping("/users")
	public class UserController {

	    @Autowired
	    private UserService userService;

	    @GetMapping("/pagid")
	    public List<User> getAllUsers() {
	        return userService.getAllUsers();
	    }
	    @GetMapping
	    public Page<User> getAllUsers(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size,
	        @RequestParam(defaultValue = "id") String sortBy
	    ) {
	        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
	        System.out.println(pageable);
	        return userService.getAllUsers(pageable);
	    }

	    
	    
	    
	    @GetMapping("/{id}")
	    public User getUserById(@PathVariable Long id) {
	        return userService.getUserById(id);
	    }
	   
	    @PostMapping
	    public User createUser(@RequestBody User user) {
	    	System.out.println("user created");
	        return userService.createUser(user);
	    }
	    @PostMapping("/with-profile")
	    public String createUserWithProfile(@RequestBody UserProfileDTO dto) {
	        User user = new User();
	        user.setName(dto.getName());
	        user.setEmail(dto.getEmail());

	        Profile profile = new Profile();
	        profile.setBio(dto.getBio());
	        profile.setPhone(dto.getPhone());

	        userService.createUserAndProfile(user, profile, dto.isFail());

	        return "User and Profile created!";
	    }
	    @PostMapping("/batch")
	    public List<User> createUsersBatch(@RequestBody List<User> users) {
	        return userService.createUsersOneByOne(users);
	    }

	}

