package com.cg.RestfulService.respository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.cg.RestfulService.entity.User;

@Repository(value = "inmemorydao") // Register this as a spring's bean
public class UserInMemoryRepository implements IDao {

	private static List<User> users = null;
	private static int userCount = 3;

	static {
		System.out.println("Inside static block of UserInMemoryRepository");

		users = new ArrayList<>();
		users.add(new User(1, "Amit", new Date()));
		users.add(new User(2, "Sumit", new Date()));
		users.add(new User(3, "Rahul", new Date()));
	}

	public UserInMemoryRepository() {
		System.out.println("Object of UserInMemoryRepository created using default constructor");
	}

	@Override
	public List<User> findAll() {
		System.out.println("Inside findAll() of UserInMemoryRepository");
		return users;
	}

	@Override
	public Optional<User> findById(int id) {
		System.out.println("Inside findById(int id) of UserInMemoryRepository");

		User user = users.stream().filter((User u) -> u.getId() == id).findAny().orElse(null);
		Optional<User> opt = Optional.ofNullable(user);

		return opt;
	}

	@Override
	public User save(User user) {
		System.out.println("Inside save() of UserInMemoryRepository");

		user.setId(++userCount);
		users.add(user);

		return user;
	}

	@Override
	public User deleteById(int id) {
		System.out.println("Inside deleteById(int id) of UserInMemoryRepository");

		Iterator<User> itr = users.iterator();

		while (itr.hasNext()) {
			User u = itr.next();

			if (u.getId() == id) {
				itr.remove();
				return u;
			}

		}

		return null;

	}

	@Override
	public Optional<User> findByName(String username) {
		System.out.println("Inside findByName(String username) of UserInMemoryRepository");

		User user = users.stream().filter((User u) -> u.getName().equals(username)).findAny().orElse(null);
		Optional<User> opt = Optional.ofNullable(user);

		return opt;
	}

}
