package com.cg.RestfulService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cg.RestfulService.entity.User;
import com.cg.RestfulService.respository.IDao;
import com.cg.RestfulService.respository.UserInMemoryRepository;
import com.cg.RestfulService.respository.UserJPARepository;

@Service // Similar to @Controller, register this class as a bean
public class UserService implements IService {

	// UserService is dependent on DAO
	@Autowired
	@Qualifier(value = "mysqldao")
	private IDao userRepository; // IDao userRepository = new SimpleJPARespository(); when value="mysqldao"
	// IDao userRepository = new UserInMemoryRespository(); when value="inmemorydao"

	@Override
	public List<User> findAll() {
		System.out.println("Inside findAll() of UserService");
		return userRepository.findAll();// SimpleJPARepository.findAll(); when value="mysqldao"
		// UserInMemoryRepository.findAll(); when value="inmemorydao"
	}

	@Override
	public User findOne(int id) {
		System.out.println("Inside findOne(int id) of UserService");
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}

	}

	@Override
	public User save(User user) {
		System.out.println("Inside save() of UserService");
		return userRepository.save(user);
	}

	@Override
	public void deleteOneUser(int id) {
		System.err.println("Inside deleteOneUser() of UserService");
		userRepository.deleteById(id);
	}

	@Override
	public User findByName(String name) {
		System.out.println("Inside findByName() of UserService");

		Optional<User> user = userRepository.findByName(name);

		if (user.isPresent()) {
			return user.get();
		}

		return null;
	}

}
