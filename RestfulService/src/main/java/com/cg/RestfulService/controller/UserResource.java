package com.cg.RestfulService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.RestfulService.entity.User;
import com.cg.RestfulService.exception.UserNotFoundException;
import com.cg.RestfulService.service.IService;

@RestController // @Controller
public class UserResource {

	@Autowired
	private IService userService;

	/**
	 * List of all users
	 */
	// @RequestMapping(value = "/users", method = RequestMethod.GET)
	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers() {
		System.out.println("Inside retrieveAllUsers() of UserResource");
		return userService.findAll();
	}

	/**
	 * Get a of specific user
	 */
	@GetMapping(path = "/users/byId/{id}")
	public User retrieveUser(@PathVariable("id") int a) throws UserNotFoundException {
		System.out.println("Inside retrieveUser(int a) of UserResource");

		User user = userService.findOne(a);
		System.out.println("Returned user is " + user);

		// Throw user defined exception
		if (user == null) {
			UserNotFoundException userNotFound = new UserNotFoundException("User not found");
			throw userNotFound;
		}

		return user;
	}

	/**
	 * Add user to list
	 */
	@PostMapping(path = "/users")
	public void createUser(@RequestBody User user) {
		System.out.println("Inside createUser(User user) of UserResource");
		User u = userService.save(user);
		System.out.println("User created: " + u);
	}

	/**
	 * Remove user from list
	 */
	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable("id") int a) {
		System.out.println("Inside deleteUser(int a) of UserResource");
		userService.deleteOneUser(a);
		System.out.println("User deleted");
	}

	/**
	 * Find user by username
	 */
	@GetMapping(path = "/users/byName/{name}")
	public User retrieveUserByName(@PathVariable("name") String username) throws UserNotFoundException {
		System.out.println("Inside retrieveUserByName(String username) of UserResource");

		User user = userService.findByName(username);
		System.out.println("Returned user is " + user);

		// Throw user defined exception
		if (user == null) {
			UserNotFoundException userNotFound = new UserNotFoundException("User not found");
			throw userNotFound;
		}

		return user;
	}

}
